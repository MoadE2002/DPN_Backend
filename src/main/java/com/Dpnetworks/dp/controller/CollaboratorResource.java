package com.Dpnetworks.dp.controller;

import com.Dpnetworks.dp.model.Collaborator;
import com.Dpnetworks.dp.service.CollabService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/collaborator")
public class CollaboratorResource {
    private final CollabService collabService;

    @Autowired
    public CollaboratorResource(CollabService collabService) {
        this.collabService = collabService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Collaborator>> getAllCollaborators() {
        List<Collaborator> collaborators = collabService.findAllCollaborators();
        return new ResponseEntity<>(collaborators, HttpStatus.OK);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Collaborator> getCollaboratorById(@PathVariable("id") Long id) {
        Collaborator collaborator = collabService.findCollaboratorById(id);
        return new ResponseEntity<>(collaborator, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Collaborator> addCollaborator(@RequestBody Collaborator collaborator) {
        Collaborator newCollaborator = collabService.addCollaborator(collaborator);
        return new ResponseEntity<>(newCollaborator, HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<Collaborator> updateCollaborator(@RequestBody Collaborator collaborator) {
        Collaborator updatedCollaborator = collabService.updateCollaborator(collaborator);
        return new ResponseEntity<>(updatedCollaborator, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Collaborator> deleteCollaborator(@PathVariable("id") Long id) {
        collabService.deleteCollaborator(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
