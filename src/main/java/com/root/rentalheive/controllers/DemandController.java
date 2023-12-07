package com.root.rentalheive.controllers;

import com.root.rentalheive.dto.DemandDto;
import com.root.rentalheive.entities.Demand;
import com.root.rentalheive.entities.Equipment;
import com.root.rentalheive.entities.EquipmentDemand;
import com.root.rentalheive.exception.InvalidDateException;
import com.root.rentalheive.services.EquipmentDemandServiceImp;
import com.root.rentalheive.services.interfaces.UserService;
import com.root.rentalheive.services.interfaces.DemandService;
import com.root.rentalheive.services.interfaces.EquipmentDemandService;
import com.root.rentalheive.services.interfaces.EquipmentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/demands")
public class DemandController {
    DemandService demandService;
    UserService userService;
    EquipmentService equipmentService;
    EquipmentDemandService equipmentDemandService;

    public DemandController(DemandService demandService, UserService userService, EquipmentService equipmentService, EquipmentDemandService equipmentDemandService) {
        this.demandService = demandService;
        this.userService = userService;
        this.equipmentService = equipmentService;
        this.equipmentDemandService = equipmentDemandService;
    }

    @GetMapping("")
    public List<Demand> getAll() {
        return demandService.getAll();
    }

    @GetMapping("/{id}")
    public Demand getDemadById(@PathVariable Long id) {
        return demandService.getDemandById(id);
    }

    @PostMapping("")
    public ResponseEntity<Demand> save(@RequestBody DemandDto demandDto) throws ParseException {
        demandService.isEquipmentAvailable(demandDto);
        Demand demand = Demand.builder()
                .user(userService.getUserById(1L))
                .build();
        Demand myDemand = demandService.save(demand);
        Arrays.stream(demandDto.getDemands()).forEach(Tdemand -> {
            EquipmentDemand equipmentDemand1 = EquipmentDemand.builder()
                    .startDate(Tdemand.getStartDate())
                    .endDate(Tdemand.getEndDate())
                    .demand(myDemand)
                    .equipment(equipmentService.getEquipmentById(Tdemand.getEquipmentId()))
                    .build();
            equipmentDemandService.save(equipmentDemand1);
        });
//        model mapper
//        track equipment
//        timer
        return new ResponseEntity<>(myDemand,HttpStatus.OK);
    }

    @PostMapping("/decline/{id}")
    public Map<String, Object> declineDemand(@PathVariable Long id) {
        return demandService.declineDemand(id);
    }
}
