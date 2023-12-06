package com.root.rentalheive.controllers;

import com.root.rentalheive.dto.EquipmentDto;
import com.root.rentalheive.entities.Equipment;
import com.root.rentalheive.services.interfaces.EquipmentService;
import com.root.rentalheive.services.interfaces.TypeServices;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/equipments")
public class EquipmentController {

    TypeServices typeServices;

    EquipmentService equipmentService;

    public EquipmentController(EquipmentService equipmentService, TypeServices typeServices) {
        this.equipmentService = equipmentService;
        this.typeServices = typeServices;
    }

    @GetMapping("")
    public List<Equipment> getEquipments(){
        return equipmentService.getEquipments();
    }
    @GetMapping("/{name}")
    public Equipment getEquipment(@PathVariable String name){
        return equipmentService.getEquipmentByName(name);
    }

    @PostMapping("")
    public Equipment addEquipment(@RequestBody EquipmentDto equipmentDto){
        Equipment equipment=Equipment.builder()
                .name(equipmentDto.getName())
                .creationDate(LocalDate.now())
                .pricePerDay(equipmentDto.getPricePerDay())
                .type(typeServices.findById(equipmentDto.getTypeId()))
                .build();
        return equipmentService.saveEquipment(equipment);
    }

    @PutMapping("")
    public Equipment updateEquipment(@RequestBody EquipmentDto equipmentdto){
        Equipment equipment= equipmentService.getEquipmentById(equipmentdto.getId());
        equipment.setName(equipmentdto.getName());
        equipment.setType(typeServices.findById(equipmentdto.getTypeId()));
        return equipmentService.updateEquipment(equipment);
    }

    @DeleteMapping("/{id}")
    public void deleteEquipment(@PathVariable Long id){
        equipmentService.deleteEquipment(equipmentService.getEquipmentById(id));
    }
}
