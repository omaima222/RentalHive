package com.root.rentalheive.services;

import com.root.rentalheive.entities.Demand;
import com.root.rentalheive.enums.DemandStatus;
import com.root.rentalheive.repositories.DemandeRepository;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class DemandService {
    DemandeRepository demandeRepository;
    DemandService(DemandeRepository demandeRepository){
        this.demandeRepository = demandeRepository;
    }
    public Demand save(Demand demand){
        return demandeRepository.save(demand);
    }
    public Demand getDemandById(Long id){
        return demandeRepository.getDemandById(id);
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
