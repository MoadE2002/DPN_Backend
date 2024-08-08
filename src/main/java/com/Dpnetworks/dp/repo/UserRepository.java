package com.Dpnetworks.dp.repo;

import com.Dpnetworks.dp.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<AppUser, Long> {

    Optional<AppUser> findById(Long id);

    AppUser findByEmail(String email);

    Optional<AppUser> findByUsername(String username);
}
