package net.javaguides.emrs.services;

import net.javaguides.emrs.data.model.Patient;
import net.javaguides.emrs.dto.request.ChangePasswordRequest;
import net.javaguides.emrs.dto.request.CreateNewUserRequest;
import net.javaguides.emrs.dto.response.UserCreatedResponse;

import java.util.List;

public interface PatientServices {
    UserCreatedResponse createNewPatient(CreateNewUserRequest request);
    Patient login(String email, String password);
    Long numberOfPatients();
    Patient findPatientById(Long patientId);
    List<Patient> getAllPatients();
    boolean changePassword(ChangePasswordRequest request);
}
