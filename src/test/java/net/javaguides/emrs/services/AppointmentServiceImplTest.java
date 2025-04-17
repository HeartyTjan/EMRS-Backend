package net.javaguides.emrs.services;

import net.javaguides.emrs.data.model.Appointment;
import net.javaguides.emrs.data.model.Doctor;
import net.javaguides.emrs.data.model.Patient;
import net.javaguides.emrs.dto.request.BookAppointmentRequest;
import net.javaguides.emrs.dto.request.UpdateAppointmentRequest;
import net.javaguides.emrs.data.repositories.AppointmentRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AppointmentServiceImplTest {

    @Autowired
    private AppointmentService appointmentService;

    @Autowired
    private PatientServices patientServices;

    @Autowired
    private DoctorService doctorService;

    @Autowired
    private AppointmentRepository appointmentRepository;

    private List<Doctor> doctors;
    private List<Patient> patients;

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @BeforeEach
    void setUp() {
        doctors = doctorService.getAllDoctors();
        patients = patientServices.getAllPatients();
    }

   @AfterEach
    void tearDown() {
       appointmentRepository.deleteAll();
    }

    @Test
    void AppointmentCanBeCreated_AppointmentCountIsOneTest() {
        BookAppointmentRequest request = new BookAppointmentRequest();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime dateTime = LocalDateTime.parse("2025-05-01 14:30:45", formatter);

        request.setDoctorID(doctors.get(0).getId());
        request.setDateAndTime(dateTime);
        request.setPatientID(patients.get(0).getId());

        Appointment appointment = appointmentService.bookAppointment(request);
        assertEquals(1,appointmentService.numberOfAppointments());
    }

    @Test
    void testBookAppointment_ThrowsException_WhenDoctorIsUnavailable() {

        BookAppointmentRequest request = new BookAppointmentRequest();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime dateTime = LocalDateTime.parse("2025-05-01 14:30:45", formatter);

        request.setDoctorID(doctors.get(0).getId());
        request.setDateAndTime(dateTime);
        request.setPatientID(patients.get(0).getId());

        Appointment appointment = appointmentService.bookAppointment(request);
        assertEquals(1,appointmentService.numberOfAppointments());

        BookAppointmentRequest secondRequest = new BookAppointmentRequest();
        DateTimeFormatter secondFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime secondDateTime = LocalDateTime.parse("2025-05-01 14:30:45", secondFormatter);
        secondRequest.setDoctorID(doctors.get(0).getId());
        secondRequest.setDateAndTime(dateTime);
        secondRequest.setPatientID(patients.get(1).getId());

        assertThrows(IllegalArgumentException.class,()-> appointmentService.bookAppointment(secondRequest));

    }

    @Test
    public void testUpdateAppointment_returnNewAppointment_AppointmentCountIsOneTest() {
        BookAppointmentRequest request = new BookAppointmentRequest();
        LocalDateTime dateTime =  LocalDateTime.parse("2025-05-01 10:30:45", FORMATTER);
        request.setDoctorID(doctors.get(0).getId());
        request.setDateAndTime(dateTime);
        request.setPatientID(patients.get(0).getId());
        Appointment appointment = appointmentService.bookAppointment(request);
      assertEquals(1,appointmentService.numberOfAppointments());

        UpdateAppointmentRequest updateAppointmentRequest = new UpdateAppointmentRequest();
        LocalDateTime secondDateTime = LocalDateTime.parse("2025-10-01 16:22:45", FORMATTER);
        updateAppointmentRequest.setDoctorId(doctors.get(0).getId());
        updateAppointmentRequest.setPatientId(patients.get(0).getId());
        updateAppointmentRequest.setAppointmentDateTime(secondDateTime);
        updateAppointmentRequest.setAppointmentId(appointment.getId());

        Appointment updatedAppointment = appointmentService.updateAppointment(updateAppointmentRequest);
        assertEquals(1,appointmentService.numberOfAppointments());
    }

    @Test
    public void testBookTwoAppointmentIsCreated_AppointmentCountIsThreeTest() {
        BookAppointmentRequest request = new BookAppointmentRequest();

        LocalDateTime dateTime =  LocalDateTime.parse("2025-05-01 10:30:45", FORMATTER);
        request.setDoctorID(doctors.get(0).getId());
        request.setDateAndTime(dateTime);
        request.setPatientID(patients.get(0).getId());
        Appointment appointment = appointmentService.bookAppointment(request);
        assertEquals(1,appointmentService.numberOfAppointments());

        BookAppointmentRequest secondRequest = new BookAppointmentRequest();
        DateTimeFormatter secondFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime secondDateTime = LocalDateTime.parse("2025-10-01 16:22:45", FORMATTER);
        secondRequest.setDoctorID(doctors.get(1).getId());
        secondRequest.setPatientID(patients.get(1).getId());
        secondRequest.setDateAndTime(secondDateTime);

        Appointment updatedAppointment = appointmentService.bookAppointment(secondRequest);
        assertEquals(2,appointmentService.numberOfAppointments());
    }

}