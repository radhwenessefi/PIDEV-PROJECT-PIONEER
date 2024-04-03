package tn.esprit.projectbackend.service;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import tn.esprit.projectbackend.Entity.BankAccount;
import tn.esprit.projectbackend.Entity.Transaction;
import tn.esprit.projectbackend.repository.BankAccountRepos;
import tn.esprit.projectbackend.repository.TransactionRepos;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

@Service
public class ReportService {

    @Autowired
    private BankAccountRepos bankAccountRepository;

    @Autowired
    private TransactionRepos transactionRepository;

    public byte[] generateBankAccountsReport() {
        List<BankAccount> bankAccounts = bankAccountRepository.findAll();
        return generatePDFReportForBankAccounts(bankAccounts);
    }

    public byte[] generateTransactionsReport() {
        List<Transaction> transactions = transactionRepository.findAll();
        return generatePDFReportForTransactions(transactions);
    }

    private byte[] generatePDFReportForBankAccounts(List<BankAccount> bankAccounts) {
        try (PDDocument document = new PDDocument(); ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
            PDPage page = new PDPage();
            document.addPage(page);

            PDPageContentStream contentStream = new PDPageContentStream(document, page);
            contentStream.setFont(PDType1Font.HELVETICA_BOLD, 12);
            contentStream.beginText();
            contentStream.newLineAtOffset(100, 700);
            contentStream.showText("Bank Accounts Report");
            contentStream.endText();
            contentStream.setFont(PDType1Font.HELVETICA, 10);

            int y = 680;
            int lineHeight = 20; // Adjust the line height to create space between lines
            for (BankAccount bankAccount : bankAccounts) {
                contentStream.beginText();
                contentStream.newLineAtOffset(100, y);
                contentStream.showText("Account Number: " + bankAccount.getAccountNumber() + "   Balance: " + bankAccount.getBalance());
                contentStream.newLine();
                contentStream.endText();
                y -= lineHeight; // Adjust vertical position for next entry
            }

            contentStream.close();
            document.save(outputStream);
            return outputStream.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
            return new byte[0];
        }
    }

    public byte[] generatePDFReportForTransactions(List<Transaction> transactions) {
        try (PDDocument document = new PDDocument(); ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
            PDPage page = new PDPage();
            document.addPage(page);

            PDPageContentStream contentStream = new PDPageContentStream(document, page);
            contentStream.setFont(PDType1Font.HELVETICA_BOLD, 12);
            contentStream.beginText();
            contentStream.newLineAtOffset(100, 700);
            contentStream.showText("Transactions Report");
            contentStream.endText();
            contentStream.setFont(PDType1Font.HELVETICA, 10);

            int y = 680;
            for (Transaction transaction : transactions) {
                contentStream.beginText();
                contentStream.newLineAtOffset(100, y);
                contentStream.showText("Transaction ID: " + transaction.getId() + "    Amount: " + transaction.getAmount());
                contentStream.newLine();
                // Add other fields as needed
                contentStream.endText();
                y -= 20; // Adjust vertical position for next entry
            }

            contentStream.close();
            document.save(outputStream);
            return outputStream.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
            return new byte[0];
        }
    }
}
