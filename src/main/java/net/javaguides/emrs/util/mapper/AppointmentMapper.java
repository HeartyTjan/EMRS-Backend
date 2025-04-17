package net.javaguides.emrs.util.mapper;

import lombok.Getter;
import lombok.Setter;
import net.javaguides.emrs.data.model.Appointment;
import net.javaguides.emrs.data.model.Doctor;
import net.javaguides.emrs.data.model.Patient;
import net.javaguides.emrs.dto.request.BookAppointmentRequest;
import net.javaguides.emrs.dto.response.AppointmentResponse;

import java.time.LocalDateTime;
@Setter
@Getter
public class AppointmentMapper {


    public static Appointment maptoAppointment(Doctor doctor, Patient patient, LocalDateTime dateTime) {
        Appointment appointment = new Appointment();
        appointment.setDoctor(doctor);
        appointment.setPatient(patient);
        appointment.setAppointmentDateTime(dateTime);
        appointment.setStatus("Scheduled");
        return appointment;
    }

    public static AppointmentResponse maptoResponse(String message, Appointment appointment) {
        AppointmentResponse response = new AppointmentResponse();
        response.setMessage(message);
        response.setAppointmentId(appointment.getId());
        return response;
    }
}
