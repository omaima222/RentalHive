package com.root.rentalheive.repositories;

import com.root.rentalheive.entities.Equipment;
import com.root.rentalheive.entities.EquipmentDemand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface EquipmentDemandRepository extends JpaRepository<EquipmentDemand,Long> {
    @Query("SELECT r FROM EquipmentDemand r " + "WHERE r.equipment = :equipment " + "AND (:endDate > r.starDate AND :startDate < r.endDate)")
    public List<EquipmentDemand>  checkAvailability(Date startDate, Date endDate, Equipment equipment);
    public List<EquipmentDemand> findByEquipment(Equipment equipment);
}