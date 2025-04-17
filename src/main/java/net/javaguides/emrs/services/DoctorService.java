package net.javaguides.emrs.services;

import net.javaguides.emrs.data.model.Doctor;
import net.javaguides.emrs.dto.request.CreateNewUserRequest;
import net.javaguides.emrs.dto.request.LoginRequest;
import net.javaguides.emrs.dto.response.GeneralResponse;
import net.javaguides.emrs.dto.response.LoginResponse;
import net.javaguides.emrs.dto.response.UserCreatedResponse;

import java.util.List;
import java.util.Optional;

public interface DoctorService {

    UserCreatedResponse createNewDoctor(CreateNewUserRequest request);

    LoginResponse login(LoginRequest request);

    Long numberOfDoctors();
    Doctor findDoctorById(Long doctorId);
    List<Doctor> getAllDoctors();
    GeneralResponse verifyDoctorAccount(String token);
}
