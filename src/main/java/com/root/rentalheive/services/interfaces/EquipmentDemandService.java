package com.root.rentalheive.services.interfaces;

import com.root.rentalheive.entities.Equipment;
import com.root.rentalheive.entities.EquipmentDemand;
import org.springframework.stereotype.Service;
import java.text.ParseException;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public interface EquipmentDemandService {
    EquipmentDemand save(EquipmentDemand equipmentDemand);
    List<EquipmentDemand> getAllEquipmentDemand();
    Optional<Long> checkAvailability(LocalDate startDate, LocalDate endDate , Long equipment) throws ParseException;
   // List<EquipmentDemand> checkAvailability(LocalDate startDate, LocalDate endDate , Equipment equipment) throws ParseException;
}
