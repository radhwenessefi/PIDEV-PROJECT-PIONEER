package tn.esprit.projectbackend.Service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.projectbackend.Entity.PortfolioInvestment;
import tn.esprit.projectbackend.Entity.User;
import tn.esprit.projectbackend.Repository.PortfolioInvestmentRepository;

@Service
@AllArgsConstructor
public class PortfolioInvestmentServiceImp implements IPortfolioInvestmentService{
    PortfolioInvestmentRepository portfolioInvestmentRepository ;
    private static User user;

    public void addPortfolioInvestment(PortfolioInvestment p) {

        try {
            //idUser=p.getUsers().getUserId();
            PortfolioInvestment portfolioInvestment =(PortfolioInvestment) portfolioInvestmentRepository.findByUsersportfolio(p.getUsersportfolio());
            if (portfolioInvestment != null) {
                p.setAmount(p.getAmount() + portfolioInvestment.getAmount());
            } else {
                portfolioInvestmentRepository.save(p);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
