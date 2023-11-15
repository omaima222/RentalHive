package com.root.rentalheive.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Demande {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;

    @OneToMany(mappedBy = "devis", cascade = CascadeType.ALL)
    private List<Devis> devis;

    @OneToMany(mappedBy = "demande", cascade = CascadeType.ALL)
    private List<EquipmentDemand> equipmentDemands;



}
