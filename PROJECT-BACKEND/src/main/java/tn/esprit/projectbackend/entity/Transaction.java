package tn.esprit.projectbackend.entity;


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
    private Long transferFrom;
    private Long transferTo;

    @Enumerated(EnumType.STRING)
    private AccountType accountType;

       /* @OneToMany
       Set<jakarta.transaction.Transaction> transactions;*/


}
