package com.root.rentalheive.dto;

import com.root.rentalheive.entities.EquipmentDemand;
import jakarta.annotation.Nullable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.MutablePropertyValues;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DemandDto {
    Long userId;
    EquipmentDemandDto[] demands;
}
