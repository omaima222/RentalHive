package com.root.rentalheive.services.interfaces;

import com.root.rentalheive.entities.Type;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TypeServices {

    Type addService(Type type);
    Type updateService(Type type);
    void deleteService(Type type);
    Type findByName(String name);
    Type findById(Long id);
    List<Type> getTypes();

}
