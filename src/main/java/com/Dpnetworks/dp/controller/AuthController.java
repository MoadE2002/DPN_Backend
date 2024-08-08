package com.Dpnetworks.dp.controller;


import com.Dpnetworks.dp.model.AppUser;
import com.Dpnetworks.dp.service.MyUserDetailsService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;
import java.util.logging.Logger;

@RestController
@RequestMapping("/login")
public class AuthController {

    @Autowired
    private MyUserDetailsService userService;

    @PostMapping
    public ResponseEntity<?> login(@RequestBody Map<String, String> loginData) {
        String email = loginData.get("email");
        String password = loginData.get("password");

        Optional<AppUser> user = userService.authenticate(email, password);

        if (user.isPresent()) {
            return new ResponseEntity<>(user.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Invalid email or password", HttpStatus.UNAUTHORIZED);
        }
    }
//    @GetMapping("/me")
//    public ResponseEntity<?> getCurrentUser() {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        if (authentication == null || !authentication.isAuthenticated()) {
//            return new ResponseEntity<>("User not authenticated", HttpStatus.UNAUTHORIZED);
//        }
//
//        String username = authentication.getName();
//        System.out.println("Authenticated username: " + username); // Debugging line
//        AppUser user = (AppUser) userService.loadUserByUsername(username);
//
//        if (user == null) {
//            return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
//        }
//
//        return new ResponseEntity<>(user, HttpStatus.OK);
//    }


}