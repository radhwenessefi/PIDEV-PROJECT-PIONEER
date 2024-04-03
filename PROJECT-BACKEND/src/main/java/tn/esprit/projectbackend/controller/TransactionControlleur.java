package tn.esprit.projectbackend.controller;

import jakarta.mail.MessagingException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.projectbackend.Iservies.ITransactionService;
import tn.esprit.projectbackend.Entity.Transaction;
import tn.esprit.projectbackend.service.EmailService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/transaction")
@AllArgsConstructor
public class TransactionControlleur {
    private final ITransactionService transactionService;
    private EmailService emailService;
    @GetMapping
    public ResponseEntity<List<Transaction>> getAllTransactions() {
        List<Transaction> transactions = transactionService.findAll();
        return ResponseEntity.ok().body(transactions);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Transaction> getTransactionById(@PathVariable Long id) {
        Optional<Transaction> transaction = transactionService.findById(id);
        return transaction.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Transaction> addTransaction(@RequestBody Transaction transaction) throws MessagingException {
        Transaction newTransaction = transactionService.add(transaction);
        return ResponseEntity.status(HttpStatus.CREATED).body(newTransaction);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Transaction> updateTransaction(@PathVariable Long id, @RequestBody Transaction transaction) {
        transaction.setId(id);
        Transaction updatedTransaction = transactionService.update(transaction);
        return ResponseEntity.ok().body(updatedTransaction);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTransaction(@PathVariable Long id) {
        transactionService.delete(id);
        return ResponseEntity.noContent().build();
    }


    @PostMapping("/api/transactions/send-email")
    public ResponseEntity<String> sendEmail(@RequestBody TransactionRequest transactionRequest) {
        try {
            // Assuming your transaction request contains recipient, amount, and other transaction details
            String recipient = transactionRequest.getRecipientEmail();
            String subject = "Transaction Notification";
            String content = "Your transaction of $" + transactionRequest.getAmount() + " has been processed.";
            emailService.sendTransactionEmail(recipient, subject, content);
            return ResponseEntity.ok("Email sent successfully.");
        } catch (MessagingException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to send email.");
        }
    }
}
