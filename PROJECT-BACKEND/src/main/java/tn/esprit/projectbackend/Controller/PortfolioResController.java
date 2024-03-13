package tn.esprit.projectbackend.Controller;


import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.projectbackend.Entity.Portfolio;
import tn.esprit.projectbackend.Service.IPortfolioService;
import org.springframework.http.ResponseEntity;


import java.util.ArrayList;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/portfolio")
public class PortfolioResController {
    IPortfolioService portfolioService;
    @GetMapping("/get-all-portfolios")
    public List<Portfolio> getAllPortfolio(){
        List<Portfolio> listAllPortfolios = portfolioService.getAllPortfolio();
        return listAllPortfolios;
    }
    @GetMapping("/get-portfolios/{idPortfolio}")
    public Portfolio  getPortfolio(@PathVariable("idPortfolio") Long blId){
        Portfolio portfolio = portfolioService.getPortfolio(blId);
        return portfolio;
    }
    @PostMapping("/add-portfolio")
    public  Portfolio addPortfolio(@RequestBody Portfolio b){
        Portfolio portfolio = portfolioService.addPortfolio(b);
        return  portfolio;
    }
    @DeleteMapping("/remove-portfolio/{portfolio-id}")
    public void  removePortfolio(@PathVariable("portfolio-id") Long blId){
        portfolioService.removePortfolio(blId);
    }
    @PutMapping("/modify-portfolio")
    public Portfolio modifyPortfolio(@RequestBody Portfolio b){
        Portfolio portfolio = portfolioService.modifyPortfolio(b);
        return portfolio ;
    }



    @PostMapping("/add-portfolio-fromAPI")
    public ResponseEntity<String> addPortfolioFromAPI() {
        try {
            List<Portfolio> portfolioList = portfolioService.fetchDataFromApi();

            for (Portfolio portfolio : portfolioList) {
                portfolioService.addPortfolio(portfolio);
            }

            return ResponseEntity.ok("Portfolios added successfully");
        } catch (Exception e) {
            // Handle exceptions here
            return ResponseEntity.status(500).body("Failed to add portfolios. Error: " + e.getMessage());
        }
    }



}
