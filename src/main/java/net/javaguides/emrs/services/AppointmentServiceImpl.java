package net.javaguides.emrs.services;

import net.javaguides.emrs.data.model.Appointment;
import net.javaguides.emrs.data.model.Doctor;
import net.javaguides.emrs.data.model.Patient;
import net.javaguides.emrs.dto.request.BookAppointmentRequest;
import net.javaguides.emrs.dto.request.UpdateAppointmentRequest;
import net.javaguides.emrs.exception.ResourceNotFoundException;
import net.javaguides.emrs.mapper.AppointmentMapper;
import net.javaguides.emrs.repositories.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class AppointmentServiceImpl implements AppointmentService {
    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private DoctorService doctorService;

    @Autowired
    private PatientServices patientServices;


    @Override
    public Appointment bookAppointment(BookAppointmentRequest request) {
        Patient patient = patientServices.findPatientById(request.getPatientID());
        Doctor doctor = doctorService.findDoctorById(request.getDoctorID());
        if(!isDoctorAvailable(doctor, request.getDateAndTime())){
            throw new IllegalArgumentException("Doctor is not available for this appointment");
        }
//        DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
//        LocalDateTime dateTime = FORMATTER);
        Appointment appointment = AppointmentMapper.maptoAppointment(doctor, patient, request.getDateAndTime());
        appointmentRepository.save(appointment);
        return appointment;
    }

    @Override
    public Appointment updateAppointment(UpdateAppointmentRequest request) {
        Doctor doctor = doctorService.findDoctorById(request.getDoctorId());
        Patient patient = patientServices.findPatientById(request.getPatientId());
        Appointment appointment = findAppointmentByID(request.getAppointmentId());
        appointmentRepository.delete(appointment);
        Appointment updateAppointment = AppointmentMapper.maptoAppointment(doctor, patient, request.getAppointmentDateTime());
        appointmentRepository.save(updateAppointment);
        return updateAppointment;
//        appointment.setDoctor(doctor);
//        appointment.setPatient(patient);
//        appointment.setAppointmentDateTime(request.getAppointmentDateTime());
//        appointment.setStatus(request.getStatus());
//        ;
//        appointmentRepository.save(appointment);
//        return appointment;
    }

    private boolean isDoctorAvailable(Doctor doctor, LocalDateTime dateAndTime) {
       List<Appointment> existingAppointment =  appointmentRepository.findByDoctorAndAppointmentDateTime(doctor,dateAndTime);
       return existingAppointment.isEmpty();
    }

    @Override
    public Long numberOfAppointments() {
        return appointmentRepository.count();
    }

    @Override
    public List<Appointment> getAllAppointments() {
        return appointmentRepository.findAll();
    }

    @Override
    public void deleteAppointmentByid(Long id) {
        appointmentRepository.deleteById(id);
    }

    @Override
    public Appointment findAppointmentByID(Long appointmentId) {
        return appointmentRepository.findById(appointmentId).orElseThrow(()-> new ResourceNotFoundException("Appointment not found"));
    }


    @Override
    public List<Appointment> getAllAppointmentsById(Long id) {
        return appointmentRepository.findAllByPatientId(id);
    }

}
