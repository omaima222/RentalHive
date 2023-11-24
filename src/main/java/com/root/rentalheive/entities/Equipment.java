package com.root.rentalheive.entities;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Equipment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private LocalDate creationDate;

    @ManyToOne()
    @JoinColumn(name = "type_id" )
    private Type type;

   @OneToMany(mappedBy = "equipment", cascade = CascadeType.ALL ,fetch = FetchType.LAZY)
    private List<EquipmentDemand> equipmentDemands;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Equipment equipment = (Equipment) o;
        return Objects.equals(id, equipment.id) && Objects.equals(name, equipment.name) && Objects.equals(creationDate, equipment.creationDate) && Objects.equals(type, equipment.type) && Objects.equals(equipmentDemands, equipment.equipmentDemands);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, creationDate, type, equipmentDemands);
    }

}
