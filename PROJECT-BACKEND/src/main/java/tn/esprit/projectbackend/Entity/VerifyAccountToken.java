package tn.esprit.projectbackend.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VerifyAccountToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id ;

    private String token ;

    private LocalDateTime expiryDateTime  ;

    @ManyToOne
    private User user ;
}
