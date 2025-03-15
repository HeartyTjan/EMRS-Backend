package net.javaguides.emrs.repositories;

import net.javaguides.emrs.data.model.MedicalRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MedicalRecordRepository extends JpaRepository<MedicalRecord,Long> {
     List<MedicalRecord> findAllByPatientId(Long id);
}
