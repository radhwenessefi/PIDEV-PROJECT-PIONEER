package tn.esprit.projectbackend.Controller;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import tn.esprit.projectbackend.Entity.Portfolio;

import java.util.List;

@RestController
@AllArgsConstructor
@NoArgsConstructor
@Slf4j
@RequestMapping("/portfolio")
public class PorfolioResController {

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
//    @PostMapping("/add-portfolios-from-api")
//    public ResponseEntity<String> addPortfoliosFromApi(){
//        List<Portfolio> portfolioListFromApi = portfolioService.fetchDataFromApi();
//        log.info("the list of portfolios is here ",portfolioListFromApi);
//        for (Portfolio portfolio : portfolioListFromApi){
//            portfolioService.addPortfolio(portfolio);
//        }
//        return ResponseEntity.ok("Portfolios added From API to the data base");
//    }
}
