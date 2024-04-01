package tn.esprit.projectbackend.entity;


import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Pack {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
     private Long id_pack;
     String Name;
     String Description;
     Float Price;

@ManyToOne
    Insurance insurances;

}
