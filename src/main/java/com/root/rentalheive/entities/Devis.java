package com.root.rentalheive.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Devis {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id ;

        @ManyToOne
        @JoinColumn(name = "demande_id")
        private Demande demande;

        @OneToOne
        @JoinColumn(name = "offer_id")
        private Offer offer;
}
