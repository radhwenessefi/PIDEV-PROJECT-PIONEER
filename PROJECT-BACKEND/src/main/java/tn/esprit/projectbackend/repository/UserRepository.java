package tn.esprit.projectbackend.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.projectbackend.Entity.User;

import java.util.Optional;


@Repository
public interface UserRepository extends JpaRepository<User,Integer> {

    Optional<User> findByEmail(String username) ;
}
