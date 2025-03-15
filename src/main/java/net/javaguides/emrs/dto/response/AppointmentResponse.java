package net.javaguides.emrs.dto.response;

import lombok.Data;

import java.security.PrivateKey;
@Data
public class AppointmentResponse {
    private String message;
    private Long appointmentId;
}
