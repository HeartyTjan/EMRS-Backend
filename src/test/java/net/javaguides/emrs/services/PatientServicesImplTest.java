package net.javaguides.emrs.services;

import net.javaguides.emrs.data.model.Patient;
import net.javaguides.emrs.dto.request.ChangePasswordRequest;
import net.javaguides.emrs.dto.request.CreateNewUserRequest;
import net.javaguides.emrs.dto.response.LoginResponse;
import net.javaguides.emrs.dto.response.UserCreatedResponse;
import net.javaguides.emrs.data.repositories.PatientRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class PatientServicesImplTest {

    @Autowired
    private PatientServices patientServices;

    @Autowired
    private PatientRepository userRepository;

    @BeforeEach
    public void setUp() {
        userRepository.deleteAll();
    }

    @Test
    public void createPatient_PatientIsCreated_CountIncreaseByOne(){
        CreateNewUserRequest request = new CreateNewUserRequest();
        request.setFirstName("John");
        request.setLastName("doe");
        request.setEmail("john@gmail.com");
        request.setPassword("johndoe");

       UserCreatedResponse response =  patientServices.createNewPatient(request);
        assertEquals(1, patientServices.numberOfPatients());
        assertEquals("Registration Successful", response.getMessage());
    }

    @Test
    public void testPatientIsCreated_countIncreaseByOne_patientCanLogin(){
        CreateNewUserRequest request = new CreateNewUserRequest();
        request.setFirstName("John");
        request.setLastName("doe");
        request.setEmail("john@gmail.com");
        request.setPassword("johndoe");

        UserCreatedResponse response =  patientServices.createNewPatient(request);
        assertEquals(1, patientServices.numberOfPatients());
        assertEquals("Registration Successful", response.getMessage());

        LoginResponse loginResponse = patientServices.login(request.getEmail(), request.getPassword());
        assertNotNull(loginResponse);

    }

    @Test
    public void testPatientCanChangePassword(){
        ChangePasswordRequest request = new ChangePasswordRequest();
        request.setPatientId(4l);
        request.setOldPassword("oldpassword");
        request.setNewPassword("newpassword");

        boolean isChanged = patientServices.changePassword(request);
        assertTrue(isChanged);

    }



}