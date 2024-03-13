package tn.esprit.projectbackend.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import org.springframework.web.client.RestTemplate;
import tn.esprit.projectbackend.Entity.Portfolio;
import tn.esprit.projectbackend.Repository.PortfolioRepository;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

@Service
@AllArgsConstructor
public class PortfolioServiceImp implements IPortfolioService {
    PortfolioRepository portfolioRepository;
        private final RestTemplate restTemplate = new RestTemplate();
    ObjectMapper mapper = new ObjectMapper();
    //    @Value("${api.url}")
// private String apiUrl;
    private static String apiTest="http://127.0.0.1:8000/apply_dbscan";
    private static final Logger logger = LoggerFactory.getLogger(PortfolioServiceImp.class);
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
    // Consumtion of the Clustring API
    public List<Portfolio> fetchDataFromApi() {
        logger.info("Raw API response: {}", apiTest);
        try {
            ResponseEntity<String> rawResponseEntity = restTemplate.getForEntity(apiTest, String.class);
            String rawResponse = rawResponseEntity.getBody();
            logger.info("Raw API response: {}", rawResponse);
            List<Portfolio> portfolioList = mapper.readValue(rawResponse, new TypeReference<List<Portfolio>>() {});
            logger.info("The list of portfolio: {}", portfolioList);
            return portfolioList;
        } catch (IOException e) {
            logger.error("Error fetching data from API: {}", e.getMessage(), e);
            // Handle the error as needed
            return Collections.emptyList();
        }
    }
}
