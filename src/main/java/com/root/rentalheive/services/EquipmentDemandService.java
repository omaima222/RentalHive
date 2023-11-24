//package com.root.rentalheive.services;
//
//import com.root.rentalheive.entities.Equipment;
//import com.root.rentalheive.entities.EquipmentDemand;
//import com.root.rentalheive.repositories.EquipmentDemandRepository;
//import org.springframework.stereotype.Service;
//
//import java.text.ParseException;
//import java.time.LocalDate;
//import java.util.Date;
//import java.util.List;
//
//@Service
//public class EquipmentDemandService {
//    EquipmentDemandRepository equipmentDemandRepository;
//    public EquipmentDemandService(EquipmentDemandRepository equipmentDemandRepository){
//        this.equipmentDemandRepository=equipmentDemandRepository;
//    }
//    public EquipmentDemand save(EquipmentDemand equipmentDemand){
//        return equipmentDemandRepository.save(equipmentDemand);
//    }
//    public List <EquipmentDemand>  checkAvailability(LocalDate startDate, LocalDate endDate , Equipment equipment) throws ParseException {
//        List <EquipmentDemand>   equipmentDemand = equipmentDemandRepository.checkAvailability(startDate,endDate,equipment);
//        return equipmentDemand;
//    }
//}
