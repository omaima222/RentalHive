package com.root.rentalheive.utils;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

public class ImageUtils {

    public static String saveImageLocally(MultipartFile file, String uploadDirectory) throws IOException{

            String fileName = generateUniqueFileName(file.getOriginalFilename());

            // Build the path where the file will be saved
            Path uploadPath = Path.of(uploadDirectory);
            Path filePath = uploadPath.resolve(fileName);

            Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

            // Return the saved file name
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
