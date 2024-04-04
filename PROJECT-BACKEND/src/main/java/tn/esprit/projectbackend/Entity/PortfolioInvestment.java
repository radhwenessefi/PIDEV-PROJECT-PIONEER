package tn.esprit.projectbackend.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PortfolioInvestment {
    @Valid
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long idPortfolioInvestement;
    @NotNull(message = "amount cant be null")
    Long amount;
    @NotNull(message = "amount cant be null")
    Date dateOfInsecription;
    @NotNull(message = "amount cant be null")
    Long takeProfit;
    @NotNull(message = "amount cant be null")
    Long stopLoss;
    @NotNull(message = "amount cant be null")
    @Enumerated(EnumType.STRING)
    OrderType orderType;

    @JsonIgnore
    @ManyToOne
    User usersportfolio;
    @JsonIgnore
    @ManyToOne
    Portfolio portfolios ;
}
