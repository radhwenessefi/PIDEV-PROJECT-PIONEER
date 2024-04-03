package tn.esprit.projectbackend.Service;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.projectbackend.Entity.PortfolioInvestment;
import tn.esprit.projectbackend.Entity.User;
import tn.esprit.projectbackend.Repository.PortfolioInvestmentRepository;
import java.util.List;

@Service
@AllArgsConstructor
public class PortfolioInvestmentServiceImp implements IPortfolioInvestmentService{

    PortfolioInvestmentRepository portfolioInvestmentRepository ;
    private static User user;

    public void addPortfolioInvestment(PortfolioInvestment p) {
        try {
            List<PortfolioInvestment> portfolioInvestments = portfolioInvestmentRepository.findByUsersportfolio(p.getUsersportfolio());
            if (!portfolioInvestments.isEmpty()) {
                PortfolioInvestment portfolioInvestment = portfolioInvestments.get(0); // Assuming you want to use the first result
                p.setAmount(p.getAmount() + portfolioInvestment.getAmount());
            } else {
                portfolioInvestmentRepository.save(p);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
