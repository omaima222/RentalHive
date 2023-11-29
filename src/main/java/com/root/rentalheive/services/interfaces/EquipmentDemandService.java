package com.root.rentalheive.services.interfaces;

import com.root.rentalheive.entities.Equipment;
import com.root.rentalheive.entities.EquipmentDemand;
import org.springframework.stereotype.Service;
import java.text.ParseException;

import java.time.LocalDate;
import java.util.List;

@Service
public interface EquipmentDemandService {
    EquipmentDemand save(EquipmentDemand equipmentDemand);
    List<EquipmentDemand> checkAvailability(LocalDate startDate, LocalDate endDate , Equipment equipment) throws ParseException;
}
