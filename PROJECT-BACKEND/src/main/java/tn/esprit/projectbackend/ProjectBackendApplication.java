package tn.esprit.projectbackend;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.crypto.password.PasswordEncoder;
import tn.esprit.projectbackend.Entity.User;
import tn.esprit.projectbackend.Entity.enumerations.Role;
import tn.esprit.projectbackend.repository.UserRepository;

@SpringBootApplication
@EnableScheduling
@EnableAsync
@RequiredArgsConstructor

public class ProjectBackendApplication  {

	public static void main(String[] args) {
		SpringApplication.run(ProjectBackendApplication.class, args);
	}

	private  final UserRepository userRepository ;
	private final PasswordEncoder passwordEncoder ;
//@PostConstruct
//  public void createAdmin() {
//        User admin = User.builder().firstname("Yasmine").lastname("Jebali").email("yasmine@projectPioneers.com")
//                .password(passwordEncoder.encode("12"))
//                .enabled(true).role(Role.ADMIN).build() ;
//
//        userRepository.save(admin) ;
//    }
}
