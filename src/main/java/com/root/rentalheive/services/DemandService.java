package com.root.rentalheive.services;

import com.root.rentalheive.dto.DemandDto;
import com.root.rentalheive.entities.Demand;
import com.root.rentalheive.entities.Equipment;
import com.root.rentalheive.enums.DemandStatus;
import com.root.rentalheive.exception.EquipmentReserved;
import com.root.rentalheive.repositories.DemandeRepository;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class DemandService {
    DemandeRepository demandeRepository;
    EquipmentDemandService equipmentDemandService;
    EquipmentService equipmentService;
    DemandService(DemandeRepository demandeRepository,EquipmentDemandService equipmentDemandService, EquipmentService equipmentService){
        this.equipmentDemandService = equipmentDemandService;
        this.demandeRepository = demandeRepository;
        this.equipmentService = equipmentService;
    }
    public Demand save(Demand demand){
        return demandeRepository.save(demand);
    }
    public Demand getDemandById(Long id){
        return demandeRepository.getDemandById(id);
    }
    public Boolean isEquipmentAvailable(DemandDto demandDto){
        Arrays.stream(demandDto.getEquipmentsIds()).forEach(equipmentId -> {
            try {
                Optional<Long> equipmentDemand = equipmentDemandService.checkAvailability(demandDto.getStartDate(), demandDto.getEndDate(), equipmentId);
                if (equipmentDemand.isPresent()) {
                    Equipment equipment = equipmentService.getEquipmentById(equipmentId);
                    throw new EquipmentReserved("Equipment " + equipment.getName() + " is reserved");
                }
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
        });
        return true;
    }
    public Map<String, Object> declineDemand(Long id){
        int rowsUpdated = demandeRepository.declineDemand(id, DemandStatus.DECLINED);

        Map<String, Object> response = new HashMap<>();

        if (rowsUpdated > 0) {
            // The demand was successfully declined, fetch the updated entity
            Demand updatedDemand = demandeRepository.findById(id).orElse(null);
            response.put("message", "Demand declined successfully");
            response.put("demand", updatedDemand);
        } else {
            // Handle the case where no rows were updated (e.g., demand not found)
            response.put("message", "Failed to decline demand. Demand not found.");
        }

        return response;

    }
}
