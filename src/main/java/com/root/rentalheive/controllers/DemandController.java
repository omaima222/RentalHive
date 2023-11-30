//package com.root.rentalheive.controllers;
//
//import com.root.rentalheive.dto.DemandDto;
//import com.root.rentalheive.entities.Demand;
//import com.root.rentalheive.entities.Equipment;
//import com.root.rentalheive.entities.EquipmentDemand;
//import com.root.rentalheive.services.EquipmentDemandServiceImp;
//import com.root.rentalheive.services.UserService;
//import com.root.rentalheive.services.interfaces.DemandService;
//import com.root.rentalheive.services.interfaces.EquipmentDemandService;
//import com.root.rentalheive.services.interfaces.EquipmentService;
//import org.springframework.web.bind.annotation.*;
//
//import java.text.ParseException;
//import java.util.List;
//import java.util.Map;
//
//@RestController
//@RequestMapping("/api/demands")
//public class DemandController {
//    DemandService demandService;
//    UserService userService;
//    EquipmentService equipmentService;
//    EquipmentDemandService equipmentDemandService;
//    public DemandController(DemandService demandService, UserService userService, EquipmentService equipmentService , EquipmentDemandService equipmentDemandService) {
//        this.demandService = demandService;
//        this.userService = userService;
//        this.equipmentService=equipmentService;
//        this.equipmentDemandService=equipmentDemandService;
//    }
//
//    @GetMapping("")
//    public List<Demand> getAll(){
//        return demandService.getAll();
//    }
//
//    @GetMapping("/{id}")
//    public Demand getDemadById(@PathVariable Long id){
//        return demandService.getDemandById(id);
//    }
//    @PostMapping("")
//    public  EquipmentDemand save(@RequestBody DemandDto demandDto) throws ParseException {
//
//        Equipment equipment = equipmentService.getEquipmentById((Long) demandDto.getEquipmentId());
//        List<EquipmentDemand> validity1 = equipmentDemandService.checkAvailability(demandDto.getDemandedDate(), demandDto.getEndDate(), equipment);
//        if (validity1.size()>0){
//            return null;
//        }
//        EquipmentDemand equipmentDemand1 = EquipmentDemand.builder()
//                .startDate(demandDto.getDemandedDate())
//                .endDate(demandDto.getEndDate())
//                .demand(demandService.getDemandById(1L))
//                .equipment(equipmentService.getEquipmentById(demandDto.getEquipmentId()))
//                .build();
//        return equipmentDemandService.save(equipmentDemand1);
//
//    }
//
//    @PostMapping("/decline/{id}")
//    public Map<String, Object> declineDemand(@PathVariable Long id){
//       return demandService.declineDemand(id);
//    }
//}
