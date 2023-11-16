package com.root.rentalheive.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Data
public class Devis {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id ;

        private float price;

        private boolean isAccepted;

        private Date startedDate;


        @ManyToOne
        @JoinColumn(name = "demand_id")
        private Demand demand;

        @OneToOne
        @JoinColumn(name = "offer_id")
        private Offer offer;
}
