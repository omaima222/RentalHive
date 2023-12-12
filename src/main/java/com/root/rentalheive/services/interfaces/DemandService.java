package com.root.rentalheive.services.interfaces;

import com.root.rentalheive.dto.DemandDto;
import com.root.rentalheive.entities.Demand;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
@Service
public interface DemandService {
    Demand save(Demand demand);
    Demand getDemandById(Long id);
    Map<String, Object> declineDemand(Long id);
    List<Demand> getAll();
    List<Demand> getAllDemandWithoutDevis();
    Boolean isEquipmentAvailable(DemandDto demandDto);
}
