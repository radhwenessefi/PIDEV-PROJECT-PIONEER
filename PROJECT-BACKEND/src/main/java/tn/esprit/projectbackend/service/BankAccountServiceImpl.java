package tn.esprit.projectbackend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.projectbackend.Entity.AccountType;
import tn.esprit.projectbackend.Iservies.IBanckAccountService;
import tn.esprit.projectbackend.Entity.BankAccount;
import tn.esprit.projectbackend.repository.BankAccountRepos;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BankAccountServiceImpl implements IBanckAccountService {
    private final BankAccountRepos bankAccountRepository;

    @Override
    public List<BankAccount> findAll() {
        return bankAccountRepository.findAll();
    }

    @Override
    public Optional<BankAccount> findById(Long id) {
        return bankAccountRepository.findById(id);
    }

    @Override
    public BankAccount add(BankAccount bankAccount) {

        bankAccount.setCreateDate(LocalDate.from(LocalDateTime.now()));
        return bankAccountRepository.save(bankAccount);
    }




    @Override
    public BankAccount update(BankAccount bankAccount) {
        Optional<BankAccount> existingBankAccount = bankAccountRepository.findById(bankAccount.getId());
        if (existingBankAccount.isPresent()) {
            bankAccount.setModifyDate(LocalDate.from(LocalDateTime.now()));

            return bankAccountRepository.save(bankAccount);
        } else {
            throw new RuntimeException("Bank Account not found with id: " + bankAccount.getId());
        }
    }

    @Override
    public void delete(Long id) {
        bankAccountRepository.deleteById(id);
    }

}
