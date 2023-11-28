package com.root.rentalheive.controllers;

import com.root.rentalheive.dto.EquipmentDto;
import com.root.rentalheive.entities.Equipment;
import com.root.rentalheive.entities.EquipmentDemand;
import com.root.rentalheive.response.EquipmentsResponse;
import com.root.rentalheive.services.EquipmentDemandService;
import com.root.rentalheive.services.EquipmentService;
import com.root.rentalheive.services.TypeServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/equipments")
public class EquipmentController {

    TypeServices typeServices;

    EquipmentService equipmentServices;
    EquipmentDemandService equipmentDemandService;

    public EquipmentController(EquipmentService equipmentServices, TypeServices typeServices, EquipmentDemandService equipmentDemandService) {
        this.equipmentServices = equipmentServices;
        this.typeServices = typeServices;
        this.equipmentDemandService= equipmentDemandService;
    }

    @GetMapping("")
    public List<EquipmentsResponse> getEquipments(){
        List<EquipmentDemand> equipments = equipmentDemandService.getAllEquipmentDemand();
        List<EquipmentsResponse> dataToReturn = new ArrayList<>();
        equipments.stream().forEach(equipmentDemand -> {
           dataToReturn.add(EquipmentsResponse.builder()
                   .equipment(equipmentDemand.getEquipment().getName())
                   .endDate(equipmentDemand.getEndDate())
                   .startDate(equipmentDemand.getStartDate())
                   .build());
        });
        return dataToReturn;
    }
    @GetMapping("/{name}")
    public Equipment getEquipment(@PathVariable String name){
        return equipmentServices.getEquipmentByName(name);
    }

    @PostMapping("")
    public Equipment addEquipment(@RequestBody EquipmentDto equipmentDto){
        Equipment equipment=Equipment.builder()
                .name(equipmentDto.getName())
//                .creationDate(Date.from(LocalDate.now()))
                .type(typeServices.findById(equipmentDto.getTypeId()))
                .build();
        return equipmentServices.saveEquipment(equipment);
    }

    @PutMapping("")
    public Equipment updateEquipment(@RequestBody EquipmentDto equipmentdto){
        Equipment equipment=equipmentServices.getEquipmentById(equipmentdto.getId());
        equipment.setName(equipmentdto.getName());
        equipment.setType(typeServices.findById(equipmentdto.getTypeId()));
        return equipmentServices.updateEquipment(equipment);
    }

    @DeleteMapping("/{id}")
    public void deleteEquipment(@PathVariable Long id){
        equipmentServices.deleteEquipment(equipmentServices.getEquipmentById(id));
    }
}
