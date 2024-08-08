package com.Dpnetworks.dp.controller;

import com.Dpnetworks.dp.model.AppUser;
import com.Dpnetworks.dp.service.MyUserDetailsService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    @Qualifier("myUserDetailsService")
    private final MyUserDetailsService userService;

    public UserController(@Qualifier("myUserDetailsService") MyUserDetailsService userService) {
        this.userService = userService;
    }


    @GetMapping("/current-user")
    public ResponseEntity<AppUser> getCurrentUser() {
        AppUser currentUser = userService.getCurrentUser();
        return ResponseEntity.ok(currentUser);
    }
//    public ResponseEntity<?> getCurrentUser(HttpServletRequest request) {
//        HttpSession session = request.getSession(false); // Get the current session without creating a new one
//
//        if (session != null) {
//            AppUser currentUser = (AppUser) session.getAttribute("user");
//
//            if (currentUser != null) {
//                return new ResponseEntity<>(currentUser, HttpStatus.OK);
//            }
//        }
//
//        return new ResponseEntity<>("No user is currently logged in", HttpStatus.UNAUTHORIZED);
//    }



}
