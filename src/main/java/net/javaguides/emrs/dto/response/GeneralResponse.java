package net.javaguides.emrs.dto.response;

import lombok.Data;

@Data
public class GeneralResponse {

    private String message;
    private String jwtToken;
}
