package com.root.rentalheive.response;

import com.root.rentalheive.entities.Equipment;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class EquipmentsResponse {
    LocalDate startDate;
    LocalDate endDate;
    String equipment;
}
