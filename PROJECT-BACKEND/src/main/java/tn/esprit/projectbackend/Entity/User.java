package tn.esprit.projectbackend.Entity;

import jakarta.persistence.*;
import lombok.*;
import tn.esprit.projectbackend.Entity.Portfolio;

import java.awt.font.NumericShaper;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long UserId;
    String FName;
    String LName;
    Number PhoneNumber;
     String email;
     String password;



   @ManyToMany(cascade = CascadeType.ALL)
   private Set<Portfolio> portfolios;

    @OneToMany(cascade = CascadeType.ALL, mappedBy="users")
    private Set<Project> projects;
    @OneToMany(cascade = CascadeType.ALL, mappedBy="usersBank")
    private Set<BankAccount> bankAccounts;




}
