package com.Dpnetworks.dp.repo;

import com.Dpnetworks.dp.model.Collaborator;
import com.Dpnetworks.dp.model.Submission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.Optional;

@Repository
public interface SubmissionRepo extends JpaRepository<Submission, Long> {
    Optional<Submission> findByCollaboratorAndSubmitDate(Collaborator collaborator, Date submitDate);
}

