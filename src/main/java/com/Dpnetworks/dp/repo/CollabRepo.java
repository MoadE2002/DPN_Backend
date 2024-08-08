package com.Dpnetworks.dp.repo;

import com.Dpnetworks.dp.model.Collaborator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface CollabRepo extends JpaRepository<Collaborator, Long> {
    Collaborator findByFullNameContainingIgnoreCase(String name);
}
