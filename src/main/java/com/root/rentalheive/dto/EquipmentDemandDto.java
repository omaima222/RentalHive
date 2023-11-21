package com.root.rentalheive.dto;

import jakarta.annotation.Nullable;
import lombok.Builder;
import lombok.Data;

import java.util.Date;
@Builder
@Data
public class EquipmentDemandDto {
    @Nullable
    Long id;
    Integer duration;
    DemandDto demandDto;
    EquipmentDto equipmentDto;
    Date endDate;
    Date starDate;

}