package com.root.rentalheive.services;

import com.root.rentalheive.entities.Equipment;
import com.root.rentalheive.entities.EquipmentDemand;
import com.root.rentalheive.repositories.EquipmentDemandRepository;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class EquipmentDemandService {
    EquipmentDemandRepository equipmentDemandRepository;
    public EquipmentDemandService(EquipmentDemandRepository equipmentDemandRepository){
        this.equipmentDemandRepository=equipmentDemandRepository;
    }
    public EquipmentDemand save(EquipmentDemand equipmentDemand){
        return equipmentDemandRepository.save(equipmentDemand);
    }
    public Optional<Long> checkAvailability(LocalDate startDate, LocalDate endDate , Long equipment) throws ParseException {
        Optional<Long> equipmentDemand = Optional.ofNullable(equipmentDemandRepository.checkAvailability(startDate,endDate,equipment));
        return equipmentDemand;
    }
    public List<EquipmentDemand> getAllEquipmentDemand(){
        return equipmentDemandRepository.getAll();
    }
}
