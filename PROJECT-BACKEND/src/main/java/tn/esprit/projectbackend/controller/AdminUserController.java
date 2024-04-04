package tn.esprit.projectbackend.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.projectbackend.Entity.User;
import tn.esprit.projectbackend.dto.responses.MessageResponse;
import tn.esprit.projectbackend.service.AdminUserService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/admin/user")
public class AdminUserController {
    private final AdminUserService adminUserService ;

    @PostMapping("/ban/{id}")
    public ResponseEntity<?> banUser(@PathVariable Integer id) {
        try {
            adminUserService.banUser(id);
        } catch(Exception e) {
            return ResponseEntity.badRequest().body(new MessageResponse(e.getMessage())) ;
        }
        return ResponseEntity.ok().body(new MessageResponse("user banned successfully"));
    }
    @PostMapping("/permit/{id}")
    public ResponseEntity<?> permitUser(@PathVariable Integer id) {
        try {
            adminUserService.permitUser(id);
        } catch(Exception e) {
            return ResponseEntity.badRequest().body(new MessageResponse(e.getMessage())) ;
        }
        return ResponseEntity.ok().body(new MessageResponse("user enabled successfully"));
    }

    @GetMapping("/withMostMoney")
    public ResponseEntity<?> userWithMostMoney() {
        User user ;
        try {
            user = adminUserService.userWithMostMoney();
        } catch(Exception e) {
            return ResponseEntity.badRequest().body(new MessageResponse(e.getMessage())) ;
        }
        return ResponseEntity.ok().body(user);
    }
}
