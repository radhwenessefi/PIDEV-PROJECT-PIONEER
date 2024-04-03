package tn.esprit.projectbackend.Controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import tn.esprit.projectbackend.Entity.PortfolioInvestment;
import tn.esprit.projectbackend.Service.PortfolioInvestmentServiceImp;

import java.util.HashMap;
import java.util.Map;

@RestController
@AllArgsConstructor
@Slf4j
public class PortfolioInvestmentResController {
    PortfolioInvestmentServiceImp portfolioInvestmentServiceImp;
    @PostMapping("/add-portfolio-Investment")
    public ResponseEntity<String> addPortfolio(@Valid @RequestBody PortfolioInvestment p) {
        log.info("The order is " + p.getOrderType());

        if (p.getOrderType().toString().equals("buy")) {
            if (p.getStopLoss() > p.getTakeProfit()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Stop Loss cannot be greater than Take Profit for a buy order.");
            } else {
                portfolioInvestmentServiceImp.addPortfolioInvestment(p);
                return ResponseEntity.ok("Portfolio investment added successfully.");
            }
        } else if (p.getOrderType().toString().equals("sell")) {
            if (p.getStopLoss() < p.getTakeProfit()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Stop Loss cannot be less than Take Profit for a sell order.");
            } else {
                portfolioInvestmentServiceImp.addPortfolioInvestment(p);
                return ResponseEntity.ok("Portfolio investment added successfully.");
            }
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid order type: " + p.getOrderType());
        }
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }

}
