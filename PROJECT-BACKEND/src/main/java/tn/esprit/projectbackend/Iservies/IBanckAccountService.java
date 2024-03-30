package tn.esprit.projectbackend.Iservies;

import tn.esprit.projectbackend.entity.BankAccount;

import java.util.List;
import java.util.Optional;

public interface IBanckAccountService {
    List<BankAccount> findAll();
    Optional<BankAccount> findById(Long id);
    BankAccount add(BankAccount bankAccount);
    BankAccount update(BankAccount bankAccount);
    void delete(Long id);

}
