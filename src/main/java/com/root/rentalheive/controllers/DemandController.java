package com.root.rentalheive.controllers;

import com.root.rentalheive.dto.DemandDto;
import com.root.rentalheive.entities.Demand;
import com.root.rentalheive.entities.Equipment;
import com.root.rentalheive.entities.EquipmentDemand;
import com.root.rentalheive.services.DemandService;
import com.root.rentalheive.services.EquipmentDemandService;
import com.root.rentalheive.services.EquipmentService;
import com.root.rentalheive.services.UserService;
import com.root.rentalheive.utils.ResponseManager;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

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
    public ResponseEntity<Demand> save(@RequestBody DemandDto demandDto) throws ParseException {
        Arrays.asList(demandDto.getEquipmentsIds()).stream().forEach(equipmentId -> {
            try {
                Optional<List<EquipmentDemand>> equipmentDemand = equipmentDemandService.checkAvailability(demandDto.getStartDate(), demandDto.getEndDate(), equipmentId);
                if (equipmentDemand.isPresent()) {
                   return ResponseManager.create("One of the equipments is already borrowed", HttpStatus.METHOD_NOT_ALLOWED);
                }
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
        });
//        Demand demand = Demand.builder()
//                .user(userService.getUserById(1L))
//                .startDate(demandDto.getStartDate())
//                .endDate(demandDto.getEndDate())
//                .build();
//        Demand myDemand =demandService.save(demand);
//        EquipmentDemand equipmentDemand1 = EquipmentDemand.builder()
//                .startDate(demandDto.getDemandedDate())
//                .endDate(demandDto.getEndDate())
//                .demand(demandService.getDemandById(1L))
//                .equipment(equipmentService.getEquipmentById(demandDto.getEquipmentId()))
//                .build();
//          model mapper
//          track equipment
//          timer

    }

    @PostMapping("/decline/{id}")
    public Map<String, Object> declineDemand(@PathVariable Long id){
       return demandService.declineDemand(id);
    }
}
