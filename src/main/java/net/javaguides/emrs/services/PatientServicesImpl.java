package net.javaguides.emrs.services;

import net.javaguides.emrs.data.model.Patient;
import net.javaguides.emrs.dto.request.ChangePasswordRequest;
import net.javaguides.emrs.dto.request.CreateNewUserRequest;
import net.javaguides.emrs.dto.response.UserCreatedResponse;
import net.javaguides.emrs.exception.InvalidLoginCredentials;
import net.javaguides.emrs.exception.PatientAlreadyExistException;
import net.javaguides.emrs.exception.ResourceNotFoundException;
import net.javaguides.emrs.mapper.Mapper;
import net.javaguides.emrs.mapper.PatientMapper;
import net.javaguides.emrs.repositories.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientServicesImpl implements PatientServices {

    @Autowired
    private PatientRepository patientRepository;

    @Override
    public UserCreatedResponse createNewPatient(CreateNewUserRequest request) {
        if(patientRepository.existsByEmail(request.getEmail())) {
            throw new PatientAlreadyExistException("Patient already exists");
        }

        Patient newUser = PatientMapper.mapRequestToPatient(request);
        patientRepository.save(newUser);
        return Mapper.mapToResponse("Registration Successful");
    }

    @Override
    public Patient login(String email, String password) {
        Patient foundPatient = findPatientByEmail(email);
        boolean isSuccess = foundPatient.getPassword().equals(password);
        if(isSuccess) {
            return foundPatient;
        }
        else{
            throw new InvalidLoginCredentials("Wrong email or password");
        }
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
}
