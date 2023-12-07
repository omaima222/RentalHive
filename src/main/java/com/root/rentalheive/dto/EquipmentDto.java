package com.root.rentalheive.dto;

import jakarta.annotation.Nullable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EquipmentDto {

    @Nullable
    Long id;
    String name;
    float pricePerDay;
    String img;
    Long typeId;
}
