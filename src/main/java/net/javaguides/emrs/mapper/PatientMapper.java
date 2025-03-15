package net.javaguides.emrs.mapper;

import net.javaguides.emrs.data.model.Patient;
import net.javaguides.emrs.dto.request.CreateNewPatientRequest;
import net.javaguides.emrs.dto.request.CreateNewUserRequest;
import net.javaguides.emrs.dto.response.LoginResponse;
import net.javaguides.emrs.dto.response.UserCreatedResponse;

public class PatientMapper {

    public static Patient mapRequestToPatient(CreateNewUserRequest request){
        Patient patient = new Patient();
        patient.setFirstName(request.getFirstName());
        patient.setLastName(request.getLastName());
        patient.setEmail(request.getEmail());
        patient.setGender(request.getGender());
        patient.setPassword(request.getPassword());
        return patient;
    }


}
