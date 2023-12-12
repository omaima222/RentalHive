package com.root.rentalheive.repositories;

import com.root.rentalheive.entities.Demand;
import com.root.rentalheive.entities.EquipmentDemand;
import com.root.rentalheive.enums.DemandStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Repository
public interface DemandeRepository extends JpaRepository<Demand, Long> {


    public Demand getDemandById(Long id);
    @Transactional
    @Modifying
    @Query("UPDATE Demand d SET d.status = :status WHERE d.id = :id")
     int declineDemand(Long id,@Param("status") DemandStatus status);
    @Query("SELECT ed FROM EquipmentDemand ed WHERE ed.demand.id = :id")
    List<EquipmentDemand> findAllByDemandId(Long id);

    List<Demand> findDemandByDevisIsNull();
}
