package tn.esprit.projectbackend.servicesImpl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.projectbackend.Iservies.ITransactionService;
import tn.esprit.projectbackend.entity.BankAccount;
import tn.esprit.projectbackend.entity.Transaction;
import tn.esprit.projectbackend.repositories.BankAccountRepos;
import tn.esprit.projectbackend.repositories.TransactionRepos;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements ITransactionService {
    private final TransactionRepos transactionRepository;
    private final BankAccountRepos bankAccountRepository;

    @Override
    public List<Transaction> findAll() {
        return transactionRepository.findAll();
    }

    @Override
    public Optional<Transaction> findById(Long id) {
        return transactionRepository.findById(id);
    }

    @Override
    public Transaction add(Transaction transaction) {
        // Vérifier si c'est un retrait ou un ajout
        // Calculer le nouveau solde du compte
        if (transaction.getAmount() < 0) {
            BankAccount account = bankAccountRepository.findById(Long.valueOf(transaction.getTransferFrom()))
                    .orElseThrow(() -> new RuntimeException("Bank account not found"));
            int newBalance = (int) (account.getBalance() - Math.abs(transaction.getAmount()));
            transaction.setTransferTo(account.getId());
            if (newBalance < 0) {
                throw new RuntimeException("Insufficient balance");
            }
            account.setBalance(newBalance);
            transactionRepository.save(transaction);
            bankAccountRepository.save(account);
        } else {
            // C'est un ajout
            // Calculer le nouveau solde du compte
            BankAccount account = bankAccountRepository.findById(Long.valueOf(transaction.getTransferTo()))
                    .orElseThrow(() -> new RuntimeException("Bank account not found"));
            int newBalance = (int) (account.getBalance() + transaction.getAmount());
            transaction.setTransferTo(account.getId());
            account.setBalance(newBalance);
            transactionRepository.save(transaction);
            bankAccountRepository.save(account);
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
