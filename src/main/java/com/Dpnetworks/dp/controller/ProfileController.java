package com.Dpnetworks.dp.controller;

import com.Dpnetworks.dp.model.AppUser;
import com.Dpnetworks.dp.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/userInfos")
public class ProfileController {

    @Autowired
    private UserRepository userRepository;

    @PutMapping("/{id}")
    public ResponseEntity<AppUser> updateUser(@PathVariable Long id, @RequestBody AppUser updatedUser) {
        AppUser existingUser = userRepository.findById(id).orElse(null);
        System.out.println(existingUser);
        if (existingUser == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        existingUser.setUsername(updatedUser.getUsername());
        existingUser.setEmail(updatedUser.getEmail());
        existingUser.setPhonenumber(updatedUser.getPhonenumber());
        existingUser.setAddress(updatedUser.getAddress());

        userRepository.save(existingUser);

        return new ResponseEntity<>(existingUser, HttpStatus.OK);
    }
}
