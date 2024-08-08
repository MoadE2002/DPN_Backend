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
@AllArgsConstructor
@NoArgsConstructor
public class Expense {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "date")
    private Date month;
    private String category;
    private String description;
    private double amount;
    private String chantier;
    private String justification;

    @ManyToOne
    @JoinColumn(name = "collabId", referencedColumnName = "id")
    private Collaborator collaborator;

    @Override
    public String toString() {
        return "Expense{" +
                "id=" + id +
                ", date=" + month +
                ", category='" + category + '\'' +
                ", description='" + description + '\'' +
                ", amount=" + amount +
                ", chantier='" + chantier + '\'' +
                ", justification='" + justification + '\'' +
                ", collaborator=" + collaborator +
                '}';
    }
}
