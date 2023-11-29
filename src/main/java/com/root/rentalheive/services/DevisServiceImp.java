package com.root.rentalheive.services;

import com.itextpdf.text.DocumentException;
import com.root.rentalheive.entities.Demand;
import com.root.rentalheive.entities.Devis;
import com.root.rentalheive.enums.DevisStatus;
import com.root.rentalheive.repositories.DemandeRepository;
import com.root.rentalheive.repositories.DevisRepository;
import com.root.rentalheive.services.interfaces.DevisService;
import com.root.rentalheive.utils.PdfAgreement;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import java.io.*;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class DevisServiceImp implements DevisService {

    DevisRepository devisRepository;
    DemandeRepository demandeRepository;

    private JavaMailSender mailSender;

    @Autowired
    public DevisServiceImp(DevisRepository devisRepository, DemandeRepository demandeRepository,JavaMailSender mailSender){
        this.devisRepository = devisRepository;
        this.demandeRepository = demandeRepository;
        this.mailSender = mailSender;
    }

    @Override
    public List<Devis>getDevis(){return this.devisRepository.findAll();}

    @Override
    public Devis saveDevis(Date date, float price, Long demand_id){
        Demand demand = this.demandeRepository.findById(demand_id).get();
        Devis devis = new Devis();
        devis.setDemand(demand);
        devis.setPrice(price);
        return this.devisRepository.save(devis);
    }

    @Override
    public void sendDevis(Long id){
        Devis devis = this.devisRepository.findById(id).get();

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo("user@gmail.com");
        message.setFrom("RentalHive@gmail.com");
        message.setSubject("Devis for your recent demand .");
        message.setText( devis.toMap().toString());

        mailSender.send(message);
    }

    @Override
    public ResponseEntity<FileSystemResource> sendAgreementWithEmail(Devis devis) throws DocumentException, IOException {
        Map<String, Object> devisMap = devis.toMap();
        String localFolderPath = "com/root/rentalheive/pdfs/";
        ByteArrayOutputStream pdfStream = PdfAgreement.generatePdfStream(devisMap, "RENTAL AGREEMENT");
        String fileName = "agreement.pdf";
        String filePath = localFolderPath + fileName;
        try (FileOutputStream fos = new FileOutputStream(filePath)) {
            fos.write(pdfStream.toByteArray());
        }
        FileSystemResource file = new FileSystemResource(new File(filePath));
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
            helper.setTo("ez.zahed.abdelaziz@gmail.com");
            helper.setFrom("RentalHive@gmail.com");
            helper.setSubject("Agreement for your recent demand .");
            helper.setText(devis.toMap().toString());

            helper.addAttachment(Objects.requireNonNull(file.getFilename()), file);
            mailSender.send(mimeMessage);
        } catch (MessagingException e) {
            // Handle exception
        }

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.set(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + fileName);
        headers.setContentLength(file.contentLength());
      return  new ResponseEntity<>(file, headers, HttpStatus.OK);
    }


    @Override
    public  void deleteDevis(Long id){
        Devis devis = this.devisRepository.findById(id).get();
        this.devisRepository.delete(devis);
    }

    @Override
    public  Devis declineDevis(Long id){
        Devis devis = this.devisRepository.findById(id).get();
        devis.setStatus(DevisStatus.DECLINED);
        return this.devisRepository.save(devis);
    }

    @Override
    public Devis getDevisById(Long id){
        return this.devisRepository.findById(id).get();
    }

}
