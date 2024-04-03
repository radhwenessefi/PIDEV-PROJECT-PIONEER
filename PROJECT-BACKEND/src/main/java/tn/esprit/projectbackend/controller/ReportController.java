package tn.esprit.projectbackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tn.esprit.projectbackend.service.ReportService;

@RestController
@RequestMapping("/api/reports")
public class ReportController {

    @Autowired
    private ReportService reportService;

    @GetMapping("/bank-accounts")
    public ResponseEntity<byte[]> generateBankAccountsReport() {
        byte[] reportBytes = reportService.generateBankAccountsReport();
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_PDF) // Set content type to octet stream
                .body(reportBytes);
    }

    @GetMapping("/transactions")
    public ResponseEntity<byte[]> generateTransactionsReport() {
        byte[] reportBytes = reportService.generateTransactionsReport();
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_PDF) // Set content type to octet stream
                .body(reportBytes);
    }
}
