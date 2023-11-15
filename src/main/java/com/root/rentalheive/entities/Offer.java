package com.root.rentalheive.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Offer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "devis_id")
    private Devis devis;
}
