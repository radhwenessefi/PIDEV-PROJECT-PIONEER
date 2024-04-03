package tn.esprit.projectbackend.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "BankAccount")
public class BankAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String accountNumber;
    private int balance;
    private LocalDate createDate;
    private LocalDate modifyDate;
    private String rib ;
    private String currency ;
    @Enumerated(EnumType.STRING)
    private AccountType accountType;

    @ManyToOne
    @JsonIgnore
    private User usersBank;


    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL)
    private Set<MicroLoan> microLoans;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL)
    private Set<Transaction> transactions;


}
