package net.javaguides.emrs.services;

import net.javaguides.emrs.data.model.Doctor;
import net.javaguides.emrs.data.model.MedicalRecord;
import net.javaguides.emrs.dto.request.AddMedicalRecordRequest;

import java.util.List;

public interface MedicalRecordService {
    void addMedicalHistory(AddMedicalRecordRequest request);

    List<MedicalRecord> getRecordsById(Long id);
}
