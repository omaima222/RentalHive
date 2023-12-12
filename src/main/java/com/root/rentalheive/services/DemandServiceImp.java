package com.root.rentalheive.services;

import com.root.rentalheive.dto.DemandDto;
import com.root.rentalheive.entities.Demand;
import com.root.rentalheive.entities.Equipment;
import com.root.rentalheive.enums.DemandStatus;
import com.root.rentalheive.exception.EquipmentReserved;
import com.root.rentalheive.exception.InvalidDateException;
import com.root.rentalheive.repositories.DemandeRepository;
import com.root.rentalheive.services.interfaces.DemandService;
import com.root.rentalheive.services.interfaces.EquipmentDemandService;
import com.root.rentalheive.services.interfaces.EquipmentService;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.*;

@Service
public class DemandServiceImp implements DemandService {
    DemandeRepository demandeRepository;
    EquipmentDemandService equipmentDemandService;
    EquipmentService equipmentService;

    DemandServiceImp(DemandeRepository demandeRepository, EquipmentDemandServiceImp equipmentDemandService, EquipmentServiceImp equipmentService) {
        this.equipmentDemandService = equipmentDemandService;
        this.demandeRepository = demandeRepository;
        this.equipmentService = equipmentService;
    }

    public Demand save(Demand demand) {
        return demandeRepository.save(demand);
    }

    public Demand getDemandById(Long id) {
        return demandeRepository.getDemandById(id);
    }

    public Boolean isEquipmentAvailable(DemandDto demandDto) {
        Arrays.stream(demandDto.getDemands()).forEach(Tdemand -> {
            if(Tdemand.getEndDate().isBefore(Tdemand.getStartDate())){
                throw new InvalidDateException("Invalid Date range"+Tdemand.getStartDate()+" "+Tdemand.getEndDate());
            }
            Optional<Long> equipmentDemand = equipmentDemandService.checkAvailability(Tdemand.getStartDate(), Tdemand.getEndDate(),equipmentService.getEquipmentById(Tdemand.getEquipmentId()) );
            if (equipmentDemand.isPresent()) {
                Equipment equipment = equipmentService.getEquipmentById(Tdemand.getEquipmentId());
                throw new EquipmentReserved("Equipment " + equipment.getName() + " is reserved from");
            }
        });
        return true;
    }

    public Map<String, Object> declineDemand(Long id) {
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

    public List<Demand> getAll() {
        return demandeRepository.findAll();
    }

    public List<Demand> getAllDemandWithoutDevis() {
        return demandeRepository.findDemandByDevisIsNull();
    }
}
