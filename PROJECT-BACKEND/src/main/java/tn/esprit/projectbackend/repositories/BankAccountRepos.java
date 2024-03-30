package tn.esprit.projectbackend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.projectbackend.entity.BankAccount;

import java.util.Optional;

public interface BankAccountRepos extends JpaRepository<BankAccount,Long> {
}
