package tn.esprit.projectbackend.controlleurs;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.projectbackend.Iservies.IBanckAccountService;
import tn.esprit.projectbackend.entity.BankAccount;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/bank-accounts")
@AllArgsConstructor
public class BankAccountController {

    private final IBanckAccountService bankAccountService;

    @GetMapping
    public ResponseEntity<List<BankAccount>> getAllBankAccounts() {
        List<BankAccount> bankAccounts = bankAccountService.findAll();
        return ResponseEntity.ok().body(bankAccounts);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BankAccount> getBankAccountById(@PathVariable Long id) {
        Optional<BankAccount> bankAccount = bankAccountService.findById(id);
        return bankAccount.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<BankAccount> addBankAccount(@RequestBody BankAccount bankAccount) {
        BankAccount newBankAccount = bankAccountService.add(bankAccount);
        return ResponseEntity.status(HttpStatus.CREATED).body(newBankAccount);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BankAccount> updateBankAccount(@PathVariable Long id, @RequestBody BankAccount bankAccount) {
        bankAccount.setId(id);
        BankAccount updatedBankAccount = bankAccountService.update(bankAccount);
        return ResponseEntity.ok().body(updatedBankAccount);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBankAccount(@PathVariable Long id) {
        bankAccountService.delete(id);
        return ResponseEntity.noContent().build();
    }
}

