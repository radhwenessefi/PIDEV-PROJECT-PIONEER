package tn.esprit.projectbackend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tn.esprit.projectbackend.Entity.Wallet;
import tn.esprit.projectbackend.repository.WalletRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class WalletServiceImp implements WalletService{

    private final UserService userService ;
    private final WalletRepository walletRepository ;
    @Scheduled(fixedDelay = 1000*5)
    @Override

    public void addInterest() {
        List<Wallet> listOfWallets = walletRepository.findAll();

        for (Wallet wallet : listOfWallets) {
            double principal = wallet.getBalance();
            double rate = 0.004; // Annual interest rate (4% as an example)
            int t = 1; // Time period (in years)

            double interest = principal * rate * t;
            double newBalance = principal + interest;
            wallet.setBalance(newBalance);
        }
    }

}
