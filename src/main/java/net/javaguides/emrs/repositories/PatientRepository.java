package net.javaguides.emrs.repositories;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import net.javaguides.emrs.data.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PatientRepository extends JpaRepository<Patient,Long> {
    Optional<Patient> findByEmail(String email);

    boolean existsByEmail(@NotNull(message = "Email is compulsory")
                          @NotBlank(message = "Email is mandatory")
                          String email);
}
