package com.root.rentalheive.services.interfaces;

import com.root.rentalheive.entities.Equipment;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public interface EquipmentService {

    Equipment saveEquipment(Equipment equipment);
    Equipment updateEquipment(Equipment equipment);
    void deleteEquipment(Equipment equipment);
    List<Equipment> getEquipments();
    Equipment getEquipmentByName(String name);
    Equipment getEquipmentById(Long id);
    String saveImage(MultipartFile file) throws IOException;
}
