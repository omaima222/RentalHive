package com.root.rentalheive.dto;

import jakarta.annotation.Nullable;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.Date;
@Builder
@Data
public class EquipmentDemandDto {
    @Nullable
    Long id;
    Long equipmentId;
    LocalDate endDate;
    LocalDate startDate;
}
