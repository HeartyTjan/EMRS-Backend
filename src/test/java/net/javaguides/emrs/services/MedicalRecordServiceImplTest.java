package net.javaguides.emrs.services;

import net.javaguides.emrs.dto.request.AddMedicalRecordRequest;
import net.javaguides.emrs.repositories.MedicalRecordRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class MedicalRecordServiceImplTest {

    @Autowired
    private MedicalRecordService medicalRecordService;

    @Autowired
    private MedicalRecordRepository medicalRecordRepository;

    @Test
    public void testMedicalRecordedCanBeAdded_CountIsOne() {
        AddMedicalRecordRequest request = new AddMedicalRecordRequest();
        request.setPatientId(4l);
        request.setDoctorId(6l);
        request.setDiagnosis("malaria");
        request.setTreatment("Lonart");

        medicalRecordService.addMedicalHistory(request);
        assertEquals(1,medicalRecordRepository.count());
    }
}