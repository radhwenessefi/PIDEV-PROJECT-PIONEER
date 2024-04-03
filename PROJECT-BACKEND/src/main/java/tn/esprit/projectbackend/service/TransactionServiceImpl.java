package tn.esprit.projectbackend.service;

import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import tn.esprit.projectbackend.Entity.TransactionType;
import tn.esprit.projectbackend.Entity.User;
import tn.esprit.projectbackend.Iservies.ITransactionService;
import tn.esprit.projectbackend.Entity.BankAccount;
import tn.esprit.projectbackend.Entity.Transaction;
import tn.esprit.projectbackend.controller.TransactionRequest;
import tn.esprit.projectbackend.repository.BankAccountRepos;
import tn.esprit.projectbackend.repository.TransactionRepos;

import java.util.List;
import java.util.Optional;

import static tn.esprit.projectbackend.Entity.TransactionType.*;
import static tn.esprit.projectbackend.Entity.TransactionType.Deposit;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements ITransactionService {
    private final TransactionRepos transactionRepository;
    private final BankAccountRepos bankAccountRepository;
    private final UserService userService;
    private final EmailService emailService;


    @Override
    public List<Transaction> findAll() {
        return transactionRepository.findAll();
    }

    @Override
    public Optional<Transaction> findById(Long id) {
        return transactionRepository.findById(id);
    }

    @Override
    public Transaction add(Transaction transaction) throws MessagingException {

        BankAccount account = bankAccountRepository.findById(Long.valueOf(transaction.getReceiverId()))
                .orElseThrow(() -> new RuntimeException("Bank account not found"));

        User user = userService.findUserById(Math.toIntExact(transaction.getReceiverId()));

        // Vérifier si c'est un retrait ou un ajout
        // Calculer le nouveau solde du compte
        if (transaction.getAmount() < 0) {
            int newBalance = (int) (account.getBalance() - Math.abs(transaction.getAmount()));
            if (newBalance < 0) {
                throw new RuntimeException("Insufficient balance");
            }
            account.setBalance(newBalance);
            transaction.setTransactionType(Withdrawal);
            transactionRepository.save(transaction);
            bankAccountRepository.save(account);
        } else {
            // C'est un ajout
            // Calculer le nouveau solde du compte
            int newBalance = (int) (account.getBalance() + transaction.getAmount());
            account.setBalance(newBalance);
            transaction.setTransactionType(Deposit);
            transactionRepository.save(transaction);
            bankAccountRepository.save(account);
            TransactionRequest transactionRequest = new TransactionRequest();
            transactionRequest.setRecipientEmail(user.getEmail());
            String subject = "Transaction Notification";
            String content = "Your transaction of $" + transaction.getAmount() + " has been processed.";
            emailService.sendTransactionEmail(transactionRequest.getRecipientEmail(), subject, content);

        }
        return transactionRepository.save(transaction);
    }

    @Override
    public Transaction update(Transaction transaction) {
        Optional<Transaction> existingTransaction = transactionRepository.findById(transaction.getId());
        if (existingTransaction.isPresent()) {
            return transactionRepository.save(transaction);
        } else {
            throw new RuntimeException("Transaction not found with id: " + transaction.getId());
        }
    }

    @Override
    public void delete(Long id) {
        transactionRepository.deleteById(id);
    }













}
