package tn.esprit.projectbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.projectbackend.Entity.BankAccount;

public interface BankAccountRepos extends JpaRepository<BankAccount,Long> {
}
