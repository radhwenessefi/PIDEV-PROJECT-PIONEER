package tn.esprit.projectbackend.Controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import tn.esprit.projectbackend.Entity.PortfolioInvestment;
import tn.esprit.projectbackend.Service.PortfolioInvestmentServiceImp;
@RestController
@AllArgsConstructor
public class PortfolioInvestmentResController {
    PortfolioInvestmentServiceImp portfolioInvestmentServiceImp;
    @PostMapping("/add-portfolio-Investment")
    public void addPortfolio(@RequestBody PortfolioInvestment p) {
        if (p.getOrderType().equals("buy")) {
            if (p.getStopLoss() > p.getTakeProfit()) {
                throw new IllegalArgumentException("Stop Loss cannot be greater than Take Profit for a buy order.");
            } else {
                portfolioInvestmentServiceImp.addPortfolioInvestment(p);
            }
        } else if (p.getOrderType().equals("sell")) {
            if (p.getStopLoss() < p.getTakeProfit()) {
                throw new IllegalArgumentException("Stop Loss cannot be less than Take Profit for a sell order.");
            } else {
                portfolioInvestmentServiceImp.addPortfolioInvestment(p);
            }
        } else {
      
            throw new IllegalArgumentException("Invalid order type: " + p.getOrderType());
        }
    }

}
