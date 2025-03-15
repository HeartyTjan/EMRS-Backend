package net.javaguides.emrs.dto.response;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserCreatedResponse {
    private String message;
    private boolean isSuccess;
}
