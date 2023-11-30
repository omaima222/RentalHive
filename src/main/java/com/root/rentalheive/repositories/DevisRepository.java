package com.root.rentalheive.repositories;

import com.root.rentalheive.entities.Devis;
import com.root.rentalheive.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DevisRepository extends JpaRepository<Devis, Long> {
    @Query("select d from Devis d where d.demand.user.id = :id")
     List<Devis> getDevisByUser(Long id);
}
