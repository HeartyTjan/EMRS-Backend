package net.javaguides.emrs.controller;

import jakarta.validation.Valid;
import net.javaguides.emrs.data.model.Appointment;
import net.javaguides.emrs.data.model.Patient;
import net.javaguides.emrs.dto.request.*;
import net.javaguides.emrs.dto.response.AppointmentResponse;
import net.javaguides.emrs.dto.response.LoginResponse;
import net.javaguides.emrs.dto.response.UserCreatedResponse;
import net.javaguides.emrs.mapper.AppointmentMapper;
import net.javaguides.emrs.mapper.Mapper;
import net.javaguides.emrs.services.AppointmentService;
import net.javaguides.emrs.services.PatientServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/patient")
public class PatientController {

    @Autowired
    private PatientServices patientServices;


    @PostMapping("/login")
    public ResponseEntity<LoginResponse> patientLogin(@RequestBody LoginRequest request) {
       Patient loggedInPatient = patientServices.login(request.getEmail(), request.getPassword());
       LoginResponse response = Mapper.mapToLoginResponse("Login successful",true, loggedInPatient);
       return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/changePassword")
    public ResponseEntity<Boolean> changePassword(@RequestBody ChangePasswordRequest request) {
        Boolean isChanged = patientServices.changePassword(request);
        return new ResponseEntity<>(isChanged, HttpStatus.OK);
    }
}
