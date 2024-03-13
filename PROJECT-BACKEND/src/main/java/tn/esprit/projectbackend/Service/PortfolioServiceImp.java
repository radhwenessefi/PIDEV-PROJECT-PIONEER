package tn.esprit.projectbackend.Service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import tn.esprit.projectbackend.Entity.Portfolio;
import tn.esprit.projectbackend.Repository.PortfolioRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class PortfolioServiceImp implements IPortfolioService {
    PortfolioRepository portfolioRepository;
    public List<Portfolio> getAllPortfolio(){
        return portfolioRepository.findAll();
    }
    public Portfolio getPortfolio(Long portfolioId){
        return portfolioRepository.findById(portfolioId).get();
    }
    public  Portfolio addPortfolio(Portfolio b){
        return portfolioRepository.save(b);
    }
    public void removePortfolio(Long portfolioId){
        portfolioRepository.deleteById(portfolioId);
    }
    public Portfolio modifyPortfolio(Portfolio portfolio){
        return portfolioRepository.save(portfolio);
    }
}
