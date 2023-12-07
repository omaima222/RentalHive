package com.root.rentalheive.entities;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.*;
import com.root.rentalheive.enums.DevisStatus;
import java.time.LocalDate;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Devis {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id ;

        private float price;

        @Enumerated(EnumType.ORDINAL)
        @Column(name="status")
        private DevisStatus status;

        @OneToOne(fetch = FetchType.EAGER)
        @Nullable
        @JoinColumn(name = "demand_id")
        private Demand demand;

        @OneToOne
        @JoinColumn(name = "offer_id")
        private Offer offer;

        @OneToOne
        @JoinColumn(name = "contrat_id")
        private Contrat contrat;

        public Map<String, Object> toMap() {
                List<Map<String, Object>> equipmentsList = this.demand.getEquipmentDemands().stream().map(x->x.toMap()).collect(Collectors.toList());
                Map<String, Object> map = new HashMap<>();
                map.put("Equipment(s)", equipmentsList);
                map.put("Total price", price);

                return map;
        }
}
