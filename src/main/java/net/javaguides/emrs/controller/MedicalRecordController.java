package net.javaguides.emrs.controller;

import net.javaguides.emrs.data.model.MedicalRecord;
import net.javaguides.emrs.dto.request.AddMedicalRecordRequest;
import net.javaguides.emrs.services.MedicalRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/medicalRecord")
@RestController
public class MedicalRecordController {
    @Autowired
    private MedicalRecordService medicalRecordService;

    @PostMapping
    @PreAuthorize("hasAnyAuthority('ROLE_DOCTOR')")
    public ResponseEntity<String> addNewMedicalRecord(@RequestBody AddMedicalRecordRequest newMedicalRecordRequest) {
        medicalRecordService.addMedicalHistory(newMedicalRecordRequest);
        return ResponseEntity.ok("Medical Record Added");
    }

    @GetMapping("{id}")
    public ResponseEntity<List<MedicalRecord>> getDMedicalRecordById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(medicalRecordService.getRecordsById(id), HttpStatus.OK);
    }
}
