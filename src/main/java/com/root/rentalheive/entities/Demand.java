package com.root.rentalheive.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Demand {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;

    private Date DemandedDate;

    @OneToMany(mappedBy = "demand", cascade = CascadeType.ALL)
    private List<Devis> devis;

    @OneToMany(mappedBy = "demand", cascade = CascadeType.ALL)
    private List<EquipmentDemand> equipmentDemands;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;



}
