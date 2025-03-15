package net.javaguides.emrs.dto.response;

import lombok.Data;

@Data
public class LoginResponse {
    private boolean isSuccess;
    private String message;
    private Object data;
}
