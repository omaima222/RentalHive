package com.root.rentalheive.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Entity
@Data
public class Equipment {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Date createdDate;

    @ManyToOne
    @JoinColumn(name = "type_id")
    private Type type;

   @OneToMany(mappedBy = "equipment", cascade = CascadeType.ALL)
    private List<EquipmentDemand> equipmentDemands;


}
