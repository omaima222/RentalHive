package com.root.rentalheive.services;

import com.root.rentalheive.entities.Equipment;
import com.root.rentalheive.repositories.EquipmentRepository;
import com.root.rentalheive.services.interfaces.EquipmentService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EquipmentServiceImp implements EquipmentService {

    EquipmentRepository equipmentRepository;

    public EquipmentServiceImp(EquipmentRepository equipmentRepository){
        this.equipmentRepository = equipmentRepository;
    }

    @Override
    public Equipment saveEquipment(Equipment equipment){
        return equipmentRepository.save(equipment);
    }

    @Override
    public Equipment updateEquipment(Equipment equipment){
        return equipmentRepository.save(equipment);
    }

    @Override
    public void deleteEquipment(Equipment equipment){
        equipmentRepository.delete(equipment);
    }


    @Override
    public List<Equipment> getEquipments(){
        return equipmentRepository.findAll();
    }

    @Override
    public Equipment getEquipmentByName(String name){
        return equipmentRepository.findByName(name);
    }

    @Override
    public Equipment getEquipmentById(Long id){
        return equipmentRepository.findById(id).get();
    }
}
