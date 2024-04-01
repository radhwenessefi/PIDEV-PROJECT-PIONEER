package tn.esprit.projectbackend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.projectbackend.entity.Transaction;

import java.util.List;
import java.util.Optional;

public interface TransactionRepos extends JpaRepository<Transaction,Long> {
}
