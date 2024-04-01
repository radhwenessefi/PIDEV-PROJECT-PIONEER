package tn.esprit.projectbackend.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.projectbackend.Entity.Wallet;

@Repository
public interface WalletRepository extends JpaRepository<Wallet,Integer> {

}
