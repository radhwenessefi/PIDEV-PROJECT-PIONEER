package tn.esprit.projectbackend.entity;

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
    @ManyToMany(mappedBy="portfolios", cascade = CascadeType.ALL)
    private Set<User> users;

}
