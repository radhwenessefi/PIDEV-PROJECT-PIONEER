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
        portfolioInvestmentServiceImp.addPortfolioInvestment(p);
    }
}
