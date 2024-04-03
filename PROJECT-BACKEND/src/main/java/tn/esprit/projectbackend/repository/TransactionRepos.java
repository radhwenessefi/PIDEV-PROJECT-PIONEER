package tn.esprit.projectbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.projectbackend.Entity.Transaction;

public interface TransactionRepos extends JpaRepository<Transaction,Long> {
}
