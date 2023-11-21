package com.root.rentalheive.controllers;

import com.root.rentalheive.dto.DemandDto;
import com.root.rentalheive.entities.Equipment;
import com.root.rentalheive.entities.EquipmentDemand;
import com.root.rentalheive.services.DemandService;
import com.root.rentalheive.services.EquipmentDemandService;
import com.root.rentalheive.services.EquipmentService;
import com.root.rentalheive.services.UserService;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/demands")
public class DemandController {
    DemandService demandService;
    UserService userService;
    EquipmentService equipmentService;
    EquipmentDemandService equipmentDemandService;

    public DemandController(DemandService demandService, UserService userService, EquipmentService equipmentService ,EquipmentDemandService equipmentDemandService) {
        this.demandService = demandService;
        this.userService = userService;
        this.equipmentService=equipmentService;
        this.equipmentDemandService=equipmentDemandService;
    }

    @PostMapping("")
    public  List < EquipmentDemand>  save(@RequestBody DemandDto demandDto) throws ParseException {
//        Demand demand = Demand.builder()
//                .user(userService.getUserById(1L)).
//                DemandedDate(Date.valueOf("2023-11-20"))
//                .build();
//        Long id =demandService.save(demand).getId();
        Date myStartDate = demandDto.getDemandedDate();
        Date myEndDate = demandDto.getEndDate();
        Equipment equipment = equipmentService.getEquipmentById((Long) demandDto.getEquipmentId());
        List<EquipmentDemand> equipmentDemands = equipmentDemandService.checkAvailability2(equipment);
        return equipmentDemands;
//        EquipmentDemand equipmentDemand1 = EquipmentDemand.builder()
//                .duration(demandDto.getDuration())
//                .starDate(demandDto.getDemandedDate())
//                .endDate(demandDto.getEndDate())
//                .demand(demandService.getDemandById(1L))
//                .equipment(equipmentService.getEquipmentById(demandDto.getEquipmentId()))
//                .build();
//
//        return equipmentDemandService.save(equipmentDemand1);

    }
}
