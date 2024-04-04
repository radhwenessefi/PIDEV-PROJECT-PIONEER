package tn.esprit.projectbackend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.projectbackend.Entity.User;
import tn.esprit.projectbackend.Entity.enumerations.Role;
import tn.esprit.projectbackend.repository.UserRepository;

import java.util.Comparator;

@Service
@RequiredArgsConstructor
public class AdminUserServiceImp implements AdminUserService{

     private final UserRepository userRepository ;

    @Override
    public void banUser(Integer id) {
        User concernedUser = userRepository.findById(id).orElse(null) ;

        if(concernedUser == null)  throw new RuntimeException("user doesn't exist") ;
        if(!concernedUser.isEnabled()) throw new RuntimeException("user already banned") ;
        concernedUser.setEnabled(false);
        userRepository.save(concernedUser) ;

    }
    @Override
    public void permitUser(Integer id) {
        User concernedUser = userRepository.findById(id).orElse(null) ;
        if(concernedUser == null)  throw new RuntimeException("user doesn't exist") ;
        if(concernedUser.isEnabled()) throw new RuntimeException("user already enabled") ;
        concernedUser.setEnabled(true);
        userRepository.save(concernedUser) ;
    }


    @Override
    public User userWithMostMoney() {
        return userRepository.findAll()
                .stream().filter(user -> user.getRole() != Role.ADMIN).max(Comparator.comparing(user ->  user.getWallet().getBalance()))
                .orElse(null) ;

    }


}
