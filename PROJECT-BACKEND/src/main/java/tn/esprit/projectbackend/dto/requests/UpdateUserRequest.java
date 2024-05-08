package tn.esprit.projectbackend.dto.requests;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import tn.esprit.projectbackend.Entity.enumerations.Role;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpdateUserRequest {
    private String firstname ;
    private String lastname ;
    private String email ;
    private String password ;
    private Role role ;
}
