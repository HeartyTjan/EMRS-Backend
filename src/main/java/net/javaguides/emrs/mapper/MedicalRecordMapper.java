package net.javaguides.emrs.mapper;

import net.javaguides.emrs.data.model.Doctor;
import net.javaguides.emrs.data.model.MedicalRecord;
import net.javaguides.emrs.data.model.Patient;
import net.javaguides.emrs.dto.request.AddMedicalRecordRequest;

import java.time.LocalDateTime;

public class MedicalRecordMapper {

    public  static MedicalRecord mapToRecord(Patient patient, Doctor doctor, AddMedicalRecordRequest request) {
        MedicalRecord medicalRecord = new MedicalRecord();
        medicalRecord.setPatient(patient);
        medicalRecord.setDoctor(doctor);
        medicalRecord.setRecordDateTime(LocalDateTime.now());
        medicalRecord.setDiagnosis(request.getDiagnosis());
        medicalRecord.setTreatment(request.getTreatment());
        return medicalRecord;
    }
}
