package net.javaguides.emrs.controller;

import lombok.RequiredArgsConstructor;
import net.javaguides.emrs.dto.request.*;
import net.javaguides.emrs.dto.response.GeneralResponse;
import net.javaguides.emrs.dto.response.LoginResponse;
import net.javaguides.emrs.services.PatientServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/patient")
@RequiredArgsConstructor
public class PatientController {


    private final PatientServices patientServices;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> patientLogin(@RequestBody LoginRequest request) {
       LoginResponse response = patientServices.login(request.getEmail(), request.getPassword());
       return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/verify")
    public ResponseEntity<GeneralResponse> verifyPatient(@RequestParam("token") String token) {
        return ResponseEntity.ok(patientServices.verifyPatientAccount(token));
    }

    @PutMapping("/changePassword")
    @PreAuthorize("hasAnyAuthority('ROLE_PATIENT')")
    public ResponseEntity<Boolean> changePassword(@RequestBody ChangePasswordRequest request) {
        Boolean isChanged = patientServices.changePassword(request);
        return new ResponseEntity<>(isChanged, HttpStatus.OK);
    }
}
