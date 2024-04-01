package tn.esprit.projectbackend.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import tn.esprit.projectbackend.Entity.enumerations.WalletStatus;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Wallet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id ;

    @Enumerated(EnumType.STRING)
    private WalletStatus status  ;

    private double balance ;
    private String ownerName ;

    @OneToOne(mappedBy = "wallet")
    @JsonBackReference
    private User user ;

}
