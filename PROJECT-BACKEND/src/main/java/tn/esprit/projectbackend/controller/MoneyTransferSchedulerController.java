package tn.esprit.projectbackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import tn.esprit.projectbackend.service.MoneyTransferSchedulerService;

import java.time.Instant;

@RestController
public class MoneyTransferSchedulerController {

    private final MoneyTransferSchedulerService moneyTransferSchedulerService;

    @Autowired
    public MoneyTransferSchedulerController(MoneyTransferSchedulerService moneyTransferSchedulerService) {
        this.moneyTransferSchedulerService = moneyTransferSchedulerService;
    }

    @PostMapping("/api/schedule-money-transfer")
    public ResponseEntity<String> scheduleMoneyTransfer(
            @RequestParam("transferTime") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Instant transferTime,
            @RequestParam("fromAccount") Long fromAccount,
            @RequestParam("toAccount") Long toAccount,
            @RequestParam("amount") double amount) {

        moneyTransferSchedulerService.scheduleMoneyTransfer(transferTime, fromAccount, toAccount, amount);
        return ResponseEntity.status(HttpStatus.CREATED).body("Money transfer scheduled successfully");
    }
}
