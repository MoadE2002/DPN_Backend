//package com.Dpnetworks.dp.controller;
//
//import com.Dpnetworks.dp.model.AppUser;
//import com.Dpnetworks.dp.repo.UserRepository;
//import com.Dpnetworks.dp.service.MyUserDetailsService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//public class RegistrationController {
//
//    @Autowired
//    private UserRepository userRepository;
//
//    @Autowired
//    private PasswordEncoder passwordEncoder;
//
//    @Autowired
//    private MyUserDetailsService userService;
//
//
//    @PostMapping("/register/user")
//    public ResponseEntity<String> createUser(@RequestBody AppUser appUser) {
//        if (userService.userExists(appUser.getEmail())) {
//            return new ResponseEntity<>("User already exists", HttpStatus.BAD_REQUEST);
//        }
//
//        appUser.setPassword(passwordEncoder.encode(appUser.getPassword()));
//        userRepository.save(appUser);
//        return new ResponseEntity<>("User registered successfully", HttpStatus.OK);
//    }
//
//    @GetMapping("/userInfo/{id}")
//    public ResponseEntity<AppUser> getAllUserInfos(@PathVariable Long id) {
//        AppUser appUser = userService.loadUserById(id);
//        return new ResponseEntity<>(appUser, HttpStatus.OK);
//    }
//}
