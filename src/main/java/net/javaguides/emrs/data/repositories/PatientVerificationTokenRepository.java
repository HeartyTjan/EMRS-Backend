package net.javaguides.emrs.data.repositories;

import net.javaguides.emrs.data.model.PatientVerificationToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientVerificationTokenRepository extends JpaRepository<PatientVerificationToken, Long> {
    PatientVerificationToken findByToken(String token);
}
