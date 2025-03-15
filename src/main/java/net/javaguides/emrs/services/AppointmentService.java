package net.javaguides.emrs.services;

import net.javaguides.emrs.data.model.Appointment;
import net.javaguides.emrs.dto.request.BookAppointmentRequest;
import net.javaguides.emrs.dto.request.UpdateAppointmentRequest;
import org.springframework.http.HttpStatusCode;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface AppointmentService {
    Appointment bookAppointment(BookAppointmentRequest request);
    Appointment updateAppointment(UpdateAppointmentRequest request);
    Long numberOfAppointments();

    Appointment findAppointmentByID(Long appointmentId);

    List<Appointment> getAllAppointmentsById(Long id);
    List<Appointment> getAllAppointments();

    void deleteAppointmentByid(Long id);
}
