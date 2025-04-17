package net.javaguides.emrs.services;

import lombok.RequiredArgsConstructor;
import net.javaguides.emrs.data.model.Doctor;
import net.javaguides.emrs.data.model.DoctorVerificationToken;
import net.javaguides.emrs.dto.request.CreateNewUserRequest;
import net.javaguides.emrs.dto.request.LoginRequest;
import net.javaguides.emrs.dto.response.GeneralResponse;
import net.javaguides.emrs.dto.response.LoginResponse;
import net.javaguides.emrs.dto.response.UserCreatedResponse;
import net.javaguides.emrs.exception.*;
import net.javaguides.emrs.util.mapper.DoctorMapper;
import net.javaguides.emrs.util.mapper.Mapper;
import net.javaguides.emrs.data.repositories.DoctorRepository;
import net.javaguides.emrs.data.repositories.DoctorVerificationTokenRepository;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DoctorServiceImpl implements DoctorService {

    private final DoctorRepository doctorRepository;
    private final PasswordEncoder passwordEncoder;
    private final DoctorVerificationTokenRepository doctorVerificationTokenRepository;
    private final JavaMailSenderImpl mailSender;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    @Override
    public UserCreatedResponse createNewDoctor(CreateNewUserRequest request) {
        if(doctorRepository.existsByEmail(request.getEmail())) {
             throw new DoctorAlreadyExistException("Doctor already exists");
        }

      Doctor newDoctor =  DoctorMapper.mapRequestToDoctor(request);
        newDoctor.setPassword(passwordEncoder.encode(newDoctor.getPassword()));
      doctorRepository.save(newDoctor);
      createVerificationAndSendToken(newDoctor);

      return Mapper.mapToResponse("Check your email to verify account");

    }

    @Override
    public LoginResponse login(LoginRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        var doctor = doctorRepository.findByEmail(request.getEmail())
                .orElseThrow(()-> new ResourceNotFoundException("User not found"));
        var jwtToken = jwtService.generateToken(doctor);
        return Mapper.mapToLoginResponse("Login Successfully", true, jwtToken, doctor);
    }

    @Override
    public Long numberOfDoctors() {
        return doctorRepository.count();
    }

    @Override
    public Doctor findDoctorById(Long doctorId) {
        return doctorRepository.findById(doctorId)
                .orElseThrow(() -> new ResourceNotFoundException("Doctor not found"));
    }

    @Override
    public List<Doctor> getAllDoctors() {
        return doctorRepository.findAll();
    }


    private void createVerificationAndSendToken(Doctor doctor){
        String token = UUID.randomUUID().toString();
        LocalDateTime expiryDate = LocalDateTime.now().plusMinutes(5);

        DoctorVerificationToken verificationToken = new DoctorVerificationToken(token,doctor,expiryDate);
        doctorVerificationTokenRepository.save(verificationToken);
        sendVerificationEmail(doctor.getEmail(), token);
    }

    private void sendVerificationEmail(String email, String token) {
        String url = "http://localhost:8080/doctor/verify" + "?token=" + token;

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject("Verify your email");
         message.setText("Click the link to verify your account: " + url);

        mailSender.send(message);
    }


    @Override
    public GeneralResponse verifyDoctorAccount(String token) {
        DoctorVerificationToken verificationToken = doctorVerificationTokenRepository.findByToken(token);

        if (verificationToken == null) {
            throw new InvalidTokenException("Invalid verification token.");
        }

        if (verificationToken.getExpiryDate().isBefore(LocalDateTime.now())) {
            throw new InvalidTokenException("Token has expired.");
        }

        Doctor doctor = verificationToken.getDoctor();
        doctor.setVerified(true);
        doctorRepository.save(doctor);

        doctorVerificationTokenRepository.delete(verificationToken);

        var jwtToken = jwtService.generateToken(doctor);
        return  Mapper.mapToGeneralResponse("Account verified successfully!", jwtToken);
    }

}