package com.Dpnetworks.dp.controller;

import com.Dpnetworks.dp.model.Submission;
import com.Dpnetworks.dp.service.SubmissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/submission")
public class SubmissionController {

    private final SubmissionService submissionService;

    @Autowired
    public SubmissionController(SubmissionService submissionService) {
        this.submissionService = submissionService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Submission>> getAllSubmissions() {
        try {
            List<Submission> submissions = submissionService.findAllSubmissions();
            return new ResponseEntity<>(submissions, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
