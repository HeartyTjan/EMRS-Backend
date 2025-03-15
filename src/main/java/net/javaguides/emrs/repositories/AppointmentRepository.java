package net.javaguides.emrs.repositories;

import net.javaguides.emrs.data.model.Appointment;
import net.javaguides.emrs.data.model.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    List<Appointment> findByDoctorAndAppointmentDateTime(Doctor doctor, LocalDateTime dateAndTime);

    List<Appointment> findAllByPatientId(Long id);
}
