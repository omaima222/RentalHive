package com.root.rentalheive.services;

import com.root.rentalheive.dto.DemandDto;
import com.root.rentalheive.entities.Demand;
import com.root.rentalheive.entities.Equipment;
import com.root.rentalheive.entities.EquipmentDemand;
import com.root.rentalheive.repositories.EquipmentDemandRepository;
import com.root.rentalheive.services.interfaces.EquipmentDemandService;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class EquipmentDemandServiceImp implements EquipmentDemandService {
    EquipmentDemandRepository equipmentDemandRepository;
    public EquipmentDemandServiceImp(EquipmentDemandRepository equipmentDemandRepository){
        this.equipmentDemandRepository=equipmentDemandRepository;
    }
    public EquipmentDemand save(EquipmentDemand equipmentDemand){
        return equipmentDemandRepository.save(equipmentDemand);
    }
    public Optional<Long> checkAvailability(LocalDate startDate, LocalDate endDate , Equipment equipment)  {
        Optional<Long> equipmentDemand = Optional.ofNullable(equipmentDemandRepository.checkAvailability(startDate,endDate,equipment));
        return equipmentDemand;
    }
    public List<EquipmentDemand> getAllEquipmentDemand(){
        return equipmentDemandRepository.getAll();
    }
}
