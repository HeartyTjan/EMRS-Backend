package net.javaguides.emrs.util.mapper;


import net.javaguides.emrs.data.model.Doctor;
import net.javaguides.emrs.dto.request.CreateNewUserRequest;
import net.javaguides.emrs.dto.response.UserCreatedResponse;


public class DoctorMapper {
    
    public static Doctor mapRequestToDoctor(CreateNewUserRequest request){
        Doctor doctor = new Doctor();
        doctor.setFirstName(request.getFirstName());
        doctor.setLastName(request.getLastName());
        doctor.setEmail(request.getEmail());
        doctor.setGender(request.getGender());
        doctor.setPassword(request.getPassword());
        doctor.setRole(request.getRole());
        return doctor;
    }


}
