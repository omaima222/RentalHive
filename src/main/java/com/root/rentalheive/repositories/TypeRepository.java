package com.root.rentalheive.repositories;

import com.root.rentalheive.entities.Type;
import com.root.rentalheive.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TypeRepository extends JpaRepository<Type, Long> {
}
