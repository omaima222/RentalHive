package com.root.rentalheive.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Entity
@Data
public class Offer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "devis_id")
    private Devis devis;

    public Map<String, Object> toMap(){
        return this.devis.toMap();
    }
}
