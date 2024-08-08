package com.Dpnetworks.dp.service;

import com.Dpnetworks.dp.model.Collaborator;
import com.Dpnetworks.dp.repo.CollabRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CollabService {
    private final CollabRepo collaboratorRepository;

    @Autowired
    public CollabService(CollabRepo collaboratorRepository) {
        this.collaboratorRepository = collaboratorRepository;
    }

    public List<Collaborator> findAllCollaborators() {
        return collaboratorRepository.findAll();
    }

    public Collaborator findCollaboratorById(Long id) {
        return collaboratorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Collaborator not found"));
    }

    public Collaborator addCollaborator(Collaborator collaborator) {
        return collaboratorRepository.save(collaborator);
    }

    public Collaborator findCollaboratorsByName(String name) {
        return collaboratorRepository.findByFullNameContainingIgnoreCase(name);
    }

    public Collaborator updateCollaborator(Collaborator collaborator) {
        return collaboratorRepository.save(collaborator);
    }

    public void deleteCollaborator(Long id) {
        collaboratorRepository.deleteById(id);
    }
}
