package net.javaguides.emrs.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import net.javaguides.emrs.dto.request.CreateNewUserRequest;
import net.javaguides.emrs.dto.response.UserCreatedResponse;
import net.javaguides.emrs.services.DoctorService;
import net.javaguides.emrs.services.PatientServices;
import org.springdoc.core.service.GenericResponseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class UserController {


    private final PatientServices patientServices;
    private final DoctorService doctorService;

    @PostMapping("/register")
    private ResponseEntity<?> createNewUser( @Valid @RequestBody CreateNewUserRequest request){
        switch (request.getRole().toLowerCase()){
            case "role_doctor" -> {
                return ResponseEntity.ok(doctorService.createNewDoctor(request));
            }
            case "role_patient" -> {
                return ResponseEntity.ok(patientServices.createNewPatient(request));
            }
            default -> {
                return  ResponseEntity.badRequest().body("Invalid  Role");
            }
        }

    }



}
