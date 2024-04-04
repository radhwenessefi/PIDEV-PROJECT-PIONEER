package tn.esprit.projectbackend.service;

import tn.esprit.projectbackend.Entity.User;

public interface AdminUserService {

    public void banUser(Integer id) ;

    public User userWithMostMoney() ;
    public void permitUser(Integer id) ;
}
