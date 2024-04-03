package tn.esprit.projectbackend.Iservies;

import jakarta.mail.MessagingException;
import tn.esprit.projectbackend.Entity.Transaction;

import java.util.List;
import java.util.Optional;

public interface ITransactionService {
    List<Transaction> findAll();
    Optional<Transaction> findById(Long id);
    Transaction add(Transaction transaction) throws MessagingException;
    Transaction update(Transaction transaction);
    void delete(Long id);
}
