package net.javaguides.emrs.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateNewPatientRequest {
    @Valid

    @NotNull(message = "First Name is compulsory")
    @NotBlank(message = "First Name is compulsory")
    private String firstName;

    @NotNull(message = "Last Name is compulsory")
    @NotBlank(message = "Last Name is compulsory")
    @Size(min = 2, max = 100, message = "Password must be more than 2 and below 100 characters")

    private String lastName;

    @NotNull(message = "Email is compulsory")
    @NotBlank(message = "Email is mandatory")
    private String email;

    @NotNull(message = "gender is compulsory")
    private String gender;

    @NotNull(message = "Password is compulsory")
    @NotBlank(message = "Password is compulsory")
    @Size(min = 5, max = 15, message = "Password must be more than 5 and below 15 characters")
    private String password;

}
