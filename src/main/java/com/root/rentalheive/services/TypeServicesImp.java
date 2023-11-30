package com.root.rentalheive.services;

import com.root.rentalheive.entities.Type;
import com.root.rentalheive.repositories.TypeRepository;
import com.root.rentalheive.services.interfaces.TypeServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TypeServicesImp implements TypeServices {

    TypeRepository typeRepository;

    @Autowired
    public TypeServicesImp(TypeRepository typeRepository){
        this.typeRepository=typeRepository;
    }

    @Override
    public Type addService(Type type) {
        return typeRepository.save(type);
    }

    @Override
    public Type updateService(Type type) {
        return typeRepository.save(type);
    }

    @Override
    public void deleteService(Type type) {
        typeRepository.delete(type);
    }

    @Override
    public Type findByName(String name) {
        if(typeRepository.findByName(name).isPresent()){
            return typeRepository.findByName(name).get();
        }
        return null;
    }

    @Override
    public Type findById(Long id){
        if(typeRepository.findById(id).isPresent()){
            return typeRepository.findById(id).get();
        }
        return null;
    }

    @Override
    public List<Type> getTypes() {
        return typeRepository.findAll();
    }
}
