package net.javaguides.emrs.dto.request;

import lombok.Data;

import java.time.LocalDateTime;
@Data
public class UpdateAppointmentRequest {
    private Long appointmentId;
    private Long patientId;
    private Long doctorId;
    private LocalDateTime appointmentDateTime;
    private String status;

}
