package net.javaguides.emrs.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import java.time.LocalDateTime;

@Data
public class BookAppointmentRequest {
    private Long doctorID;
    private Long patientID;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime dateAndTime;

}
