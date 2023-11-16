package com.root.rentalheive.services;

import com.root.rentalheive.entities.Equipment;
import com.root.rentalheive.repositories.EquipmentRepository;

import java.util.List;

public class EquipmentService {

    EquipmentRepository equipmentRepository;

    public EquipmentService(EquipmentRepository equipmentRepository){
        this.equipmentRepository = equipmentRepository;
    }

    public Equipment saveEquipment(Equipment equipment){
        return null;
    }

    public Equipment updateEquipment(Equipment equipment){
        return null;
    }

    public void deleteEquipment(Equipment equipment){

    }
    public List<Equipment> getEquipments(){
        return null;
    }
}
