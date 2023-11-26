package com.root.rentalheive.entities;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.root.rentalheive.enums.DemandStatus;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Demand {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;

    private LocalDate startDate;

    @Enumerated(EnumType.ORDINAL)
    private DemandStatus status;

    @JsonProperty("endDate")
    private LocalDate endDate;

    @OneToOne
    @JoinColumn(name = "devis_id")
    private Devis devis;

    @Nullable
    @OneToMany(mappedBy = "demand", cascade = CascadeType.ALL ,fetch = FetchType.LAZY)
    private List<EquipmentDemand> equipmentDemands;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

}
