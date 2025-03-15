package net.javaguides.emrs.controller;

import jakarta.servlet.http.HttpSession;
import net.javaguides.emrs.data.model.Appointment;
import net.javaguides.emrs.dto.request.BookAppointmentRequest;
import net.javaguides.emrs.dto.request.UpdateAppointmentRequest;
import net.javaguides.emrs.dto.response.AppointmentResponse;
import net.javaguides.emrs.mapper.AppointmentMapper;
import net.javaguides.emrs.services.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/appointment")

public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;

    @PostMapping("/book")
    private ResponseEntity<AppointmentResponse> bookAppointment(@RequestBody BookAppointmentRequest request) {
        Appointment appointment = appointmentService.bookAppointment(request);
        AppointmentResponse response = AppointmentMapper.maptoResponse("Appointment Booked Successfully",appointment);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/update")
    private  ResponseEntity<AppointmentResponse> updateAppointment(@RequestBody UpdateAppointmentRequest request) {
        Appointment appointment = appointmentService.updateAppointment(request);
        AppointmentResponse response = AppointmentMapper.maptoResponse("Appointment Updated Successfully",appointment);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @GetMapping("{id}")
    private ResponseEntity<List<Appointment>> getAppointmentsById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(appointmentService.getAllAppointmentsById(id), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    private ResponseEntity<String> deleteAppointmentById(@PathVariable("id") Long id) {
        appointmentService.deleteAppointmentByid(id);
        return ResponseEntity.ok("Appointment Canceled");
    }

}
