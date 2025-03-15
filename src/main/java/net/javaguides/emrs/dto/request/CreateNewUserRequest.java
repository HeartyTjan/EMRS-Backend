package net.javaguides.emrs.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateNewUserRequest {
    private String firstName;
    private String lastName;
    private String email;
    private String gender;
    private String password;
    private String role;
}
