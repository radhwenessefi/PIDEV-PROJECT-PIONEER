package tn.esprit.projectbackend.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Portfolio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long idPortfolio;
    String symbol;
    String volume;
    Float adjClose;
    @OneToMany(cascade = CascadeType.ALL, mappedBy="portfolios")
    private Set<PortfolioInvestment> portfolioInvestments;
}
