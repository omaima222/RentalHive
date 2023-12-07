package com.root.rentalheive.controllers;

import com.root.rentalheive.dto.EquipmentDto;
import com.root.rentalheive.entities.Equipment;
import com.root.rentalheive.services.interfaces.EquipmentService;
import com.root.rentalheive.services.interfaces.TypeServices;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;


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
    public List<Equipment> getEquipments() {
        return equipmentService.getEquipments();
    }

    @GetMapping("/{name}")
    public Equipment getEquipment(@PathVariable String name) {
        return equipmentService.getEquipmentByName(name);
    }

    @PostMapping(value = "",  produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody Equipment addEquipment(@RequestParam("img") MultipartFile file,
                                                @RequestParam("name") String name,
                                                @RequestParam("pricePerDay") float pricePerDay,
                                                @RequestParam("typeId") Long typeId) throws IOException {
        // Create an EquipmentDto
        EquipmentDto equipmentDto = new EquipmentDto();
        equipmentDto.setName(name);
        equipmentDto.setPricePerDay(pricePerDay);
        equipmentDto.setTypeId(typeId);

        String imageName = equipmentService.saveImage(file);

        Equipment equipment = Equipment.builder()
                .name(equipmentDto.getName())
                .imag(imageName)
                .creationDate(LocalDate.now())
                .pricePerDay(equipmentDto.getPricePerDay())
                .type(typeServices.findById(equipmentDto.getTypeId()))
                .build();
        return equipmentService.saveEquipment(equipment);
    }



    @PutMapping("")
    public Equipment updateEquipment(@RequestBody EquipmentDto equipmentdto) {
        Equipment equipment = equipmentService.getEquipmentById(equipmentdto.getId());
        equipment.setName(equipmentdto.getName());
        equipment.setType(typeServices.findById(equipmentdto.getTypeId()));
        return equipmentService.updateEquipment(equipment);
    }

    @DeleteMapping("/{id}")
    public void deleteEquipment(@PathVariable Long id) {
        equipmentService.deleteEquipment(equipmentService.getEquipmentById(id));
    }
}
