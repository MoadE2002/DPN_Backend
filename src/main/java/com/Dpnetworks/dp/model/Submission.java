package com.Dpnetworks.dp.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Submission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "submit_date")
    private Date submitDate;
    private double total;
    @Column(name = "is_submitted", nullable = false, columnDefinition = "boolean default false")
    private boolean isSubmitted;

    @ManyToOne
    @JoinColumn(name = "collabId", referencedColumnName = "id")
    private Collaborator collaborator;
}
