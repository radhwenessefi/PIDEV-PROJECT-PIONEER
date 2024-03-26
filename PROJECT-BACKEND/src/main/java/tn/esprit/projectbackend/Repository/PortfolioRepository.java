package tn.esprit.projectbackend.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import tn.esprit.projectbackend.Entity.Portfolio;

import java.util.List;
import java.util.Map;

public interface PortfolioRepository extends JpaRepository<Portfolio,Long> {

    @Query(value = "SELECT * FROM portfolio GROUP BY cluster_labels", nativeQuery = true)
    List<Map<String, Portfolio>> findPortfoliosGroupedByClusterLabel();
}
