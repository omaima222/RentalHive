package com.root.rentalheive.controllers;

import com.root.rentalheive.dto.DemandDto;
import com.root.rentalheive.entities.Demand;
import com.root.rentalheive.entities.Equipment;
import com.root.rentalheive.entities.EquipmentDemand;
import com.root.rentalheive.exception.EquipmentReserved;
import com.root.rentalheive.exception.InvalidDateException;
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
    public DemandController(DemandService demandService, UserService userService, EquipmentService equipmentService, EquipmentDemandService equipmentDemandService) {
        this.demandService = demandService;
        this.userService = userService;
        this.equipmentService = equipmentService;
        this.equipmentDemandService = equipmentDemandService;
    }

    @PostMapping("")
    public ResponseEntity<Demand> save(@RequestBody DemandDto demandDto) throws ParseException {
        demandService.isEquipmentAvailable(demandDto);
        Demand demand = Demand.builder()
                .user(userService.getUserById(1L))
                .build();
        Demand myDemand = demandService.save(demand);
        Arrays.stream(demandDto.getDemands()).forEach(Tdemand -> {
            if(Tdemand.getEndDate().isBefore(Tdemand.getStartDate())){
                throw new InvalidDateException("Invalid Date range"+Tdemand.getStartDate()+" "+Tdemand.getEndDate());
            }
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
