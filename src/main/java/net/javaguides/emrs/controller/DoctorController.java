package net.javaguides.emrs.controller;

import net.javaguides.emrs.data.model.Doctor;
import net.javaguides.emrs.dto.request.LoginRequest;
import net.javaguides.emrs.dto.response.LoginResponse;
import net.javaguides.emrs.mapper.Mapper;
import net.javaguides.emrs.services.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/doctor")
public class DoctorController {

    @Autowired
    private DoctorService doctorService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> doctorLogin(@RequestBody LoginRequest request) {
        Doctor loggedInDoctor = doctorService.login(request.getEmail(), request.getPassword());
        LoginResponse response = Mapper.mapToLoginResponse("Login successful",true, loggedInDoctor);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/getDoctors")
    @PreAuthorize("hasAnyAuthority('ROLE_DOCTOR')")
    public ResponseEntity<List<Doctor>> getDoctors() {
        return new ResponseEntity<>(doctorService.getAllDoctors(), HttpStatus.OK);
    }
}
