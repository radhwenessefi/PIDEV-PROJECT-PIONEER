package tn.esprit.projectbackend.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PortfolioInvestment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long idPortfolioInvestement;
    Long amount;
    Date dateOfInsecription;
    Long takeProfit;
    Long stopLoss;

    @Enumerated(EnumType.STRING)
    OrderType orderType;


    @ManyToOne
    User usersportfolio;

    @ManyToOne
    Portfolio portfolios ;
}