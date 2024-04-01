package tn.esprit.projectbackend.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.projectbackend.Entity.ResetPasswordToken;
import tn.esprit.projectbackend.Entity.User;

import java.util.Optional;

@Repository
public interface ResetPasswordTokenRepository extends JpaRepository<ResetPasswordToken,Integer> {

    void removeAllByUser(User user) ;
    Optional<ResetPasswordToken> findByToken(String token) ;

}
