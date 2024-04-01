package tn.esprit.projectbackend.dto.responses;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@AllArgsConstructor
@Getter
@Setter
public class MessageResponse {
    private String message;
}
