package tn.esprit.projectbackend.controller;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class TransactionRequest {

    private String recipientEmail;
    private double amount;
}
