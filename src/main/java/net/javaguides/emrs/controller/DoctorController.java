package net.javaguides.emrs.controller;

import lombok.RequiredArgsConstructor;
import net.javaguides.emrs.data.model.Doctor;
import net.javaguides.emrs.dto.request.LoginRequest;
import net.javaguides.emrs.dto.response.GeneralResponse;
import net.javaguides.emrs.dto.response.LoginResponse;
import net.javaguides.emrs.services.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/doctor")
@RequiredArgsConstructor
public class DoctorController {

    private final DoctorService doctorService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> doctorLogin(@RequestBody LoginRequest request) {
        LoginResponse response = doctorService.login(request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/verify")
    public ResponseEntity<GeneralResponse> verifyDoctorAccount(@RequestParam("token") String token) {
        return ResponseEntity.ok(doctorService.verifyDoctorAccount(token));
    }


    @GetMapping("/getDoctors")
    @PreAuthorize("hasAnyAuthority('ROLE_DOCTOR')")
    public ResponseEntity<List<Doctor>> getDoctors() {
        return new ResponseEntity<>(doctorService.getAllDoctors(), HttpStatus.OK);
    }
}
