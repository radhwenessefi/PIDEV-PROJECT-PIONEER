package tn.esprit.projectbackend.service;

import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.stereotype.Service;
import tn.esprit.projectbackend.Entity.Transaction;

import java.time.Instant;

@Service

public class MoneyTransferSchedulerService {

    private final TaskScheduler taskScheduler;
    private final TransactionServiceImpl transactionService;

    @Autowired
    public MoneyTransferSchedulerService( @Qualifier("taskScheduler")TaskScheduler taskScheduler, TransactionServiceImpl transactionService) {
        this.taskScheduler = taskScheduler;
        this.transactionService = transactionService;
    }

    public void scheduleMoneyTransfer(Instant transferTime, Long fromAccount, Long toAccount, double amount) {
        // Create a Runnable task to perform the money transfer
        Runnable task = () -> {
            Transaction transaction = new Transaction();
            transaction.setAmount((long) amount);
            transaction.setSenderId(fromAccount);
            transaction.setReceiverId(toAccount);
            transaction.setDate(Instant.now()); // You may need to set the transaction date
            try {
                transactionService.add(transaction); // Call the add method to perform the money transfer
            } catch (MessagingException e) {
                throw new RuntimeException(e);
            }
        };

        // Schedule the task to execute at the specified time
        taskScheduler.schedule(task, transferTime);
    }
}
