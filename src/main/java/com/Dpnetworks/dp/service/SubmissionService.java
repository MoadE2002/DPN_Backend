package com.Dpnetworks.dp.service;

import com.Dpnetworks.dp.model.Collaborator;
import com.Dpnetworks.dp.model.Submission;
import com.Dpnetworks.dp.repo.SubmissionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubmissionService {
    @Autowired
    private SubmissionRepo submissionRepository;

    public List<Submission> findAllSubmissions() {
        return submissionRepository.findAll();
    }

    @Autowired
    public SubmissionService(SubmissionRepo submissionRepo) {
        this.submissionRepository = submissionRepo;
    }
    public Submission addSubmission(Submission submission) {

        return submissionRepository.save(submission);
    }


}
