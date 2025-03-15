package net.javaguides.emrs.dto.request;

import lombok.Data;

@Data
public class ChangePasswordRequest {
    private Long patientId;
    private String oldPassword;
    private String newPassword;
}
