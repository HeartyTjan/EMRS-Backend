package net.javaguides.emrs.data.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.OnDelete;


import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
public class PatientVerificationToken {
    @Id
    @GeneratedValue
    private Long id;

    private String token;

    @OneToOne(cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
    @JoinColumn(name = "patient_id", nullable = false)
    private Patient patient;
    private LocalDateTime expiryDate;



    public PatientVerificationToken(String token, Patient patient, LocalDateTime expiryDate) {
        this.token = token;
        this.patient = patient;
        this.expiryDate = expiryDate;
    }
}

