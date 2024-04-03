package tn.esprit.projectbackend.Entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.Instant;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder

@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "Transaction")

public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Instant Date;
    private Long amount;
    private Long senderId;
    private Long receiverId;
    private TransactionType transactionType;




    @JsonIgnore

    @Enumerated(EnumType.STRING)
    private AccountType accountType;




    @JsonIgnore

    @ManyToOne
 BankAccount bankAccount ;


}
