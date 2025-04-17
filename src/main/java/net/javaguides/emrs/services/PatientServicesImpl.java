package net.javaguides.emrs.services;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import net.javaguides.emrs.data.model.PatientVerificationToken;
import net.javaguides.emrs.data.model.Patient;
import net.javaguides.emrs.dto.request.ChangePasswordRequest;
import net.javaguides.emrs.dto.request.CreateNewUserRequest;
import net.javaguides.emrs.dto.response.GeneralResponse;
import net.javaguides.emrs.dto.response.LoginResponse;
import net.javaguides.emrs.dto.response.UserCreatedResponse;
import net.javaguides.emrs.exception.InvalidTokenException;
import net.javaguides.emrs.exception.PatientAlreadyExistException;
import net.javaguides.emrs.exception.ResourceNotFoundException;
import net.javaguides.emrs.util.mapper.Mapper;
import net.javaguides.emrs.util.mapper.PatientMapper;
import net.javaguides.emrs.data.repositories.PatientRepository;
import net.javaguides.emrs.data.repositories.PatientVerificationTokenRepository;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class PatientServicesImpl implements PatientServices {


    private final PatientRepository patientRepository;
    private final PasswordEncoder passwordEncoder;
    private final PatientVerificationTokenRepository patientVerificationTokenRepository;
    private final JavaMailSenderImpl mailSender;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;


    @Override
    @Transactional
    public UserCreatedResponse createNewPatient(CreateNewUserRequest request) {
        if(patientRepository.existsByEmail(request.getEmail())) {
            throw new PatientAlreadyExistException("Patient already exists");
        }

        Patient newUser = PatientMapper.mapRequestToPatient(request);
        newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
        patientRepository.save(newUser);
        createVerificationAndSendToken(newUser);
        return Mapper.mapToResponse("Check your email to verify account");
    }

    @Override
    public LoginResponse login(String email, String password) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
        var patient = patientRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("Patient not found"));

        String jwtToken = jwtService.generateToken(patient);
        return Mapper.mapToLoginResponse("Login Successfully",true,jwtToken,patient);
    }

    @Override
    public Long numberOfPatients() {
        return patientRepository.count();
    }

    @Override
    public Patient findPatientById(Long patientId) {
        return patientRepository.findById(patientId).
                orElseThrow(() -> new ResourceNotFoundException("Patient not found"));
    }

    @Override
    public List<Patient> getAllPatients() {
        return patientRepository.findAll();
    }

    @Override
    public boolean changePassword(ChangePasswordRequest request) {
       Patient foundPatient =  findPatientById(request.getPatientId());
       if(foundPatient.getPassword().equals(request.getOldPassword())) {
           foundPatient.setPassword(request.getNewPassword());
           return true;
       }
       else {
           return false;
       }
    }

    private Patient findPatientByEmail(String email) {
        return patientRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("Patient not Found" ));
    }

    private void createVerificationAndSendToken(Patient patient){
        String token = UUID.randomUUID().toString();
        LocalDateTime expiryDate = LocalDateTime.now().plusMinutes(5);

        PatientVerificationToken verificationToken = new PatientVerificationToken(token,patient,expiryDate);
        patientVerificationTokenRepository.save(verificationToken);
        sendVerificationEmail(patient.getEmail(), token);
    }

    private void sendVerificationEmail(String email, String token) {
        String url = "http://localhost:8080/patient/verify" + "?token=" + token;

            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(email);
            message.setSubject("Verify your email");
            message.setText("Click the link to verify your account: " + url);

            mailSender.send(message);
    }

    @Override
    public GeneralResponse verifyPatientAccount(String token) {
        PatientVerificationToken verificationToken = patientVerificationTokenRepository.findByToken(token);

        if (verificationToken == null) {
            throw new InvalidTokenException("Invalid verification token.");
        }

        if (verificationToken.getExpiryDate().isBefore(LocalDateTime.now())) {
            throw new InvalidTokenException("Token has expired.");
        }

        Patient patient = verificationToken.getPatient();
        patient.setVerified(true);
        patientRepository.save(patient);
        patientVerificationTokenRepository.delete(verificationToken);

        var jwtToken = jwtService.generateToken(patient);

        return Mapper.mapToGeneralResponse("Account verified successfully!", jwtToken);
    }
}
