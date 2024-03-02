package tn.esprit.projectbackend.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.projectbackend.Entity.Portfolio;

public interface PortfolioRepository extends JpaRepository<Portfolio,Long> {
}
