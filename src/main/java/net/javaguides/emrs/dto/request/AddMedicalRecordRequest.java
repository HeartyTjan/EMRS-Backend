package net.javaguides.emrs.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AddMedicalRecordRequest {
    @Valid

    @NotNull(message = "Must not be null")
    @NotBlank(message = "Must not be null")
    private Long patientId;
    private Long doctorId;

    @NotNull(message = "Must not be null")
    @NotBlank(message = "Must not be null")
    private String diagnosis;

    @NotNull(message = "Must not be null")
    @NotBlank(message = "Must not be null")
    private String treatment;
}
