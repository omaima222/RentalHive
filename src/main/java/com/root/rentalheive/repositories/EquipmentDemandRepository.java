package com.root.rentalheive.repositories;

import com.root.rentalheive.entities.Equipment;
import com.root.rentalheive.entities.EquipmentDemand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface EquipmentDemandRepository extends JpaRepository<EquipmentDemand,Long> {
    //@Query("SELECT r FROM EquipmentDemand r  WHERE r.equipment = :equipment AND (:endDate is not bettween r.startDate AND r.endDate ) and   (:startDate is not bettween r.startDate AND r.endDate )")
    @Query("SELECT max(r.id) FROM EquipmentDemand r " +
            "WHERE r.equipment = :equipment " +
            "AND (:endDate > r.startDate AND :startDate < r.endDate)")
    public Long  checkAvailability(LocalDate startDate, LocalDate endDate,Equipment  equipment);
    public List<EquipmentDemand> findByEquipment(Equipment equipment);
    @Query("SELECT  r FROM EquipmentDemand r ORDER BY r.equipment.id")
    public List<EquipmentDemand> getAll();
}
