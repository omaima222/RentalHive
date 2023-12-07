package com.root.rentalheive.services;

import com.root.rentalheive.entities.Equipment;
import com.root.rentalheive.repositories.EquipmentRepository;
import com.root.rentalheive.services.interfaces.EquipmentService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.UUID;

@Service
public class EquipmentServiceImp implements EquipmentService {

    EquipmentRepository equipmentRepository;
    S3Service s3Service;

    public EquipmentServiceImp(EquipmentRepository equipmentRepository, S3Service s3Service) {
        this.s3Service = s3Service;
        this.equipmentRepository = equipmentRepository;
    }

    @Override
    public Equipment saveEquipment(Equipment equipment){
        return equipmentRepository.save(equipment);
    }

    @Override
    public Equipment updateEquipment(Equipment equipment){
        return equipmentRepository.save(equipment);
    }

    @Override
    public void deleteEquipment(Equipment equipment){
        equipmentRepository.delete(equipment);
    }


    @Override
    public List<Equipment> getEquipments(){
        return equipmentRepository.findAll();
    }

    @Override
    public Equipment getEquipmentByName(String name){
        return equipmentRepository.findByName(name);
    }

    @Override
    public Equipment getEquipmentById(Long id){
        return equipmentRepository.findById(id).get();
    }

    public   String saveImage(MultipartFile file) throws IOException {

        String fileName = generateUniqueFileName(file.getOriginalFilename());
        s3Service.uploadFile(fileName,file);
        return fileName;

    }

    private static String generateUniqueFileName(String originalFileName) {
        // Generate a unique identifier using UUID
        String uniqueIdentifier = UUID.randomUUID().toString().replace("-", "");

        // Extract the file extension from the original filename
        String fileExtension = originalFileName.substring(originalFileName.lastIndexOf("."));

        // Combine the unique identifier and file extension to create a unique filename
        return uniqueIdentifier + fileExtension;
    }
}
