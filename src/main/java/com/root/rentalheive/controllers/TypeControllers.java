package com.root.rentalheive.controllers;
import com.root.rentalheive.dto.TypeDto;
import com.root.rentalheive.entities.Type;
import com.root.rentalheive.services.TypeServicesImp;
import com.root.rentalheive.services.interfaces.TypeServices;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping( "/api/types")
public class TypeControllers {
    private TypeServices typeServices;
    TypeControllers(TypeServices typeServices){
        this.typeServices = typeServices;
    }

    @GetMapping("")
    public List<Type> getTypes() {
        return typeServices.getTypes();
    }

    @GetMapping("/{name}")
    public Type getType(@PathVariable String name){
        return typeServices.findByName(name);
    }

    @PostMapping("")
    public Type addType(@RequestBody TypeDto typeDto){
        Type type = new Type();
        type.setName(typeDto.getName());
        return typeServices.addService(type);
    }

    @PutMapping("")
    public Type updateType(@RequestBody TypeDto typeDto){
        Type type = new Type();
        type.setName(typeDto.getName());
        type.setId(typeDto.getId());
        return typeServices.updateService(type);
    }

    @DeleteMapping("/{id}")
    public void deleteType(@PathVariable Long id){
        typeServices.deleteService(typeServices.findById(id));
    }
}
