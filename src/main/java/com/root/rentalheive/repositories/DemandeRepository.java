package com.root.rentalheive.repositories;

import com.root.rentalheive.entities.Demand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DemandeRepository extends JpaRepository<Demand, Long> {
}
