package net.javaguides.emrs.util.mapper;

import net.javaguides.emrs.dto.response.GeneralResponse;
import net.javaguides.emrs.dto.response.LoginResponse;
import net.javaguides.emrs.dto.response.UserCreatedResponse;

public class Mapper {
    public static UserCreatedResponse mapToResponse(String message){
        UserCreatedResponse response = new UserCreatedResponse();

        response.setMessage(message);
        response.setSuccess(true);
        return response;
    }

    public static LoginResponse mapToLoginResponse(String message, boolean isSuccess,String jwtToken, Object data ){
        LoginResponse response = new LoginResponse();
        response.setMessage(message);
        response.setSuccess(isSuccess);
        response.setJwtToken(jwtToken);
        response.setData(data);
        return response;
    }

    public static GeneralResponse mapToGeneralResponse(String message, String jwtToken){
        GeneralResponse response = new GeneralResponse();
        response.setMessage(message);
        response.setJwtToken(jwtToken);
        return response;
    }
}
