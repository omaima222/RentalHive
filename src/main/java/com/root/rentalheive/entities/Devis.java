package com.root.rentalheive.entities;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.root.rentalheive.enums.DevisStatus;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Entity
@Data
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Devis {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id ;

        private float price;

        private DevisStatus status;

        @OneToOne
        @Nullable
        @JoinColumn(name = "demand_id")
        @JsonIgnore
        private Demand demand;

        @OneToOne
        @JoinColumn(name = "offer_id")
        @JsonIgnore
        private Offer offer;

        public Map<String, Object> toMap() {


                List<Map<String, Object>> equipmentsList = this.demand.getEquipmentDemands().stream().map(x->x.toMap()).collect(Collectors.toList());

                Map<String, Object> map = new HashMap<>();
                map.put("Equipment(s)", equipmentsList);
                map.put("Total price", this.price);
                map.put("Demand date", this.demand.getStartDate());

                return map;
        }
}
