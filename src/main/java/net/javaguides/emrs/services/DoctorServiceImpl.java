package net.javaguides.emrs.services;

import lombok.RequiredArgsConstructor;
import net.javaguides.emrs.data.model.Doctor;
import net.javaguides.emrs.data.model.Patient;
import net.javaguides.emrs.dto.request.CreateNewUserRequest;
import net.javaguides.emrs.dto.request.LoginRequest;
import net.javaguides.emrs.dto.response.UserCreatedResponse;
import net.javaguides.emrs.exception.DoctorAlreadyExistException;
import net.javaguides.emrs.exception.InvalidLoginCredentials;
import net.javaguides.emrs.exception.PatientAlreadyExistException;
import net.javaguides.emrs.exception.ResourceNotFoundException;
import net.javaguides.emrs.mapper.DoctorMapper;
import net.javaguides.emrs.mapper.Mapper;
import net.javaguides.emrs.repositories.DoctorRepository;
import net.javaguides.emrs.repositories.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DoctorServiceImpl implements DoctorService {

    private final DoctorRepository doctorRepository;
    private final PasswordEncoder passwordEncoder;
    @Override
    public UserCreatedResponse createNewDoctor(CreateNewUserRequest request) {
        if(doctorRepository.existsByEmail(request.getEmail())) {
             throw new DoctorAlreadyExistException("Doctor already exists");
        }

      Doctor newDoctor =  DoctorMapper.mapRequestToDoctor(request);
        newDoctor.setPassword(passwordEncoder.encode(newDoctor.getPassword()));

      doctorRepository.save(newDoctor);
      return Mapper.mapToResponse("Registration Successful");

    }

    @Override
    public Doctor login(String email, String password) {
       Doctor foundDoctor = findDoctorByEmail(email);
       boolean isSuccess = foundDoctor.getPassword().equals(password);
       if(isSuccess) {
           return foundDoctor;
       }
       else{
           throw new InvalidLoginCredentials("Wrong email or password");
       }
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


    private Doctor findDoctorByEmail(String email) {
        return doctorRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("Doctor not Found" ));
    }

}