package net.javaguides.emrs.mapper;

import net.javaguides.emrs.dto.response.LoginResponse;
import net.javaguides.emrs.dto.response.UserCreatedResponse;

public class Mapper {
    public static UserCreatedResponse mapToResponse(String message){
        UserCreatedResponse response = new UserCreatedResponse();

        response.setMessage(message);
        response.setSuccess(true);
        return response;
    }

    public static LoginResponse mapToLoginResponse(String message, boolean isSuccess,Object data){
        LoginResponse response = new LoginResponse();
        response.setMessage(message);
        response.setSuccess(isSuccess);
        response.setData(data);
        return response;
    }
}
