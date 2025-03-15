package net.javaguides.emrs.dto.request;

import lombok.Data;
import net.javaguides.emrs.data.model.Patient;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class BookAppointmentRequest {
    private Long doctorID;
    private Long patientID;
    private LocalDateTime dateAndTime;

}
