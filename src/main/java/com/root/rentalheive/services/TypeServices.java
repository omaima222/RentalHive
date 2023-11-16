package com.root.rentalheive.services;

import com.root.rentalheive.entities.Type;
import com.root.rentalheive.repositories.TypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class TypeServices {

    TypeRepository typeRepository;

    public TypeServices(TypeRepository typeRepository){
        this.typeRepository=typeRepository;
    }

    public Type addService(Type type) {
        return null;
    }

    public Type updateService(Type type) {
        return null;
    }

    public void deleteService(Type type) {
        //return false;
    }

    public List<Type> getTypes() {
        return null;
    }
}
