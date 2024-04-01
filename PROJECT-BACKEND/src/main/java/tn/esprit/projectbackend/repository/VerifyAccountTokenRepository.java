package tn.esprit.projectbackend.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.projectbackend.Entity.User;
import tn.esprit.projectbackend.Entity.VerifyAccountToken;

import java.util.Optional;

@Repository
public interface VerifyAccountTokenRepository extends JpaRepository<VerifyAccountToken,Integer> {
    void removeAllByUser(User user) ;
    Optional<VerifyAccountToken> findByToken(String token) ;
}
