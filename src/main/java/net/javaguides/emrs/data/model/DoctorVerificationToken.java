package net.javaguides.emrs.data.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.print.Doc;
import java.time.LocalDateTime;

@Entity
@Data
@RequiredArgsConstructor
public class DoctorVerificationToken {
    @Id
    @GeneratedValue
    private Long id;

    private String token;

    @OneToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "doctor_id", nullable = false)
    private Doctor doctor;
    private LocalDateTime expiryDate;

    public DoctorVerificationToken(String token, Doctor doctor, LocalDateTime expiryDate) {
        this.token = token;
        this.doctor = doctor;
        this.expiryDate = expiryDate;
    }


}
