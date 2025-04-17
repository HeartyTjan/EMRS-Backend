package net.javaguides.emrs.data.repositories;


import net.javaguides.emrs.data.model.DoctorVerificationToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DoctorVerificationTokenRepository extends JpaRepository<DoctorVerificationToken, Long> {
    DoctorVerificationToken findByToken(String token);
}

