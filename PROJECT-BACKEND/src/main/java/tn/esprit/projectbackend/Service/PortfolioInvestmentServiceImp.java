package tn.esprit.projectbackend.Service;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.StreamingHttpOutputMessage;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import tn.esprit.projectbackend.Entity.Portfolio;
import tn.esprit.projectbackend.Entity.PortfolioInvestment;
import tn.esprit.projectbackend.Entity.User;
import tn.esprit.projectbackend.Repository.PortfolioInvestmentRepository;
import tn.esprit.projectbackend.Repository.PortfolioRepository;
import tn.esprit.projectbackend.Repository.UserRepository;

import java.util.List;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class PortfolioInvestmentServiceImp implements IPortfolioInvestmentService{

    PortfolioInvestmentRepository portfolioInvestmentRepository ;
    UserRepository userRepository;
    PortfolioRepository portfolioRepository;
    private static User user;
    @Autowired
    private JavaMailSender mailSender;

    public void addPortfolioInvestment(PortfolioInvestment p,Long userId, Long portfolioId) {
        try {
            User user1 = userRepository.findById(userId).get();
            Portfolio portfolio = portfolioRepository.findById(portfolioId).get();
            p.setUsersportfolio(user1);
            p.setPortfolios(portfolio);

            List<PortfolioInvestment> portfolioInvestmentsuser = portfolioInvestmentRepository.findByUsersportfolio(p.getUsersportfolio());
            List<PortfolioInvestment> portfolioInvestments = portfolioInvestmentRepository.findByPortfolios(p.getPortfolios());
            if (!portfolioInvestmentsuser.isEmpty() && !portfolioInvestments.isEmpty()) {
                PortfolioInvestment portfolioInvestment = portfolioInvestments.get(0); // Assuming you want to use the first result
                portfolioInvestment.setAmount(p.getAmount() + portfolioInvestment.getAmount());
                portfolioInvestmentRepository.save(portfolioInvestment);
            } else {
                portfolioInvestmentRepository.save(p);
            }
        } catch (Exception e) {

            e.printStackTrace();
        }
    }
    public void closeOrder(Long investId){
        portfolioInvestmentRepository.deleteById(investId);
    }
    public void sendEmail(String to, String subject, String body){

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("essefi.radhwen@gmail.com");
        message.setTo(to);
        message.setSubject(subject);
        message.setText(body);
        mailSender.send(message);
    }
}
