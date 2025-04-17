package net.javaguides.emrs.services;

import lombok.RequiredArgsConstructor;
import net.javaguides.emrs.data.model.Doctor;
import net.javaguides.emrs.data.model.MedicalRecord;
import net.javaguides.emrs.data.model.Patient;
import net.javaguides.emrs.dto.request.AddMedicalRecordRequest;
import net.javaguides.emrs.util.mapper.MedicalRecordMapper;
import net.javaguides.emrs.data.repositories.MedicalRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MedicalRecordServiceImpl implements MedicalRecordService{

    private final PatientServices patientService;
    private final DoctorService doctorService;
    private final MedicalRecordRepository medicalRecordRepository;

    @Override
    public void addMedicalHistory(AddMedicalRecordRequest request) {

        Patient foundPatient = patientService.findPatientById(request.getPatientId());
        Doctor foundDoctor = doctorService.findDoctorById(request.getDoctorId());

        MedicalRecord newMedicalRecord = MedicalRecordMapper.mapToRecord(foundPatient, foundDoctor, request);
        medicalRecordRepository.save(newMedicalRecord);
    }

    @Override
    public List<MedicalRecord> getRecordsById(Long id) {
        return medicalRecordRepository.findAllByPatientId(id);
    }


}
