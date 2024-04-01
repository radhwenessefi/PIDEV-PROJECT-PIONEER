package tn.esprit.projectbackend.config;

import org.springframework.security.core.userdetails.UserDetailsService;
import tn.esprit.projectbackend.service.JwtService;

public class JwtAuthenticationFilterImpl extends JwtAuthenticationFilter {
    public JwtAuthenticationFilterImpl(JwtService jwtService, UserDetailsService userDetailsService) {
        super(jwtService, userDetailsService);
    }
}
