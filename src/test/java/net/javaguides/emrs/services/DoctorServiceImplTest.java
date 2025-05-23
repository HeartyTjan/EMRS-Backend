package net.javaguides.emrs.services;

import net.javaguides.emrs.dto.request.CreateNewUserRequest;
import net.javaguides.emrs.dto.request.LoginRequest;
import net.javaguides.emrs.dto.response.LoginResponse;
import net.javaguides.emrs.dto.response.UserCreatedResponse;
import net.javaguides.emrs.data.repositories.DoctorRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class DoctorServiceImplTest {

    @Autowired
    private DoctorService doctorService;
    
    @Autowired
    private DoctorRepository doctorRepository;
    @BeforeEach
    void setUp() {
        doctorRepository.deleteAll();
    }

    @Test
    public void createDoctor_DoctorIsCreated_CountIncreaseByOne() {
        CreateNewUserRequest request = new CreateNewUserRequest();
        request.setFirstName("John");
        request.setLastName("doe");
        request.setEmail("john@gmail.com");
        request.setPassword("johndoe");

        UserCreatedResponse response = doctorService.createNewDoctor(request);
        assertEquals(1, doctorService.numberOfDoctors());
        assertEquals("Registration Successful", response.getMessage());
    }

    @Test
    public void testDoctorIsCreated_countIncreaseByOne_DoctorCanLogin() {
        CreateNewUserRequest request = new CreateNewUserRequest();
        request.setFirstName("John");
        request.setFirstName("John");
        request.setLastName("doe");
        request.setEmail("john@gmail.com");
        request.setPassword("johndoe");

        UserCreatedResponse response = doctorService.createNewDoctor(request);
        assertEquals(1, doctorService.numberOfDoctors());
        assertEquals("Registration Successful", response.getMessage());

        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setEmail("john@gmail.com");
        loginRequest.setPassword("johndoe");

        LoginResponse loginResponse = doctorService.login(loginRequest);
        assertNotNull(loginResponse);

    }
}