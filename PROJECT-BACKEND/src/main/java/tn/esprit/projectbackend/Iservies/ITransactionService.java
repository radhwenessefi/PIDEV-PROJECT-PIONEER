package tn.esprit.projectbackend.Iservies;

import tn.esprit.projectbackend.entity.Transaction;
import tn.esprit.projectbackend.repositories.TransactionRepos;

import java.util.List;
import java.util.Optional;

public interface ITransactionService {
    List<Transaction> findAll();
    Optional<Transaction> findById(Long id);
    Transaction add(Transaction transaction);
    Transaction update(Transaction transaction);
    void delete(Long id);
}
