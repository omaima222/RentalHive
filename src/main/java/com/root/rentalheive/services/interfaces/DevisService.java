package com.root.rentalheive.services.interfaces;

import com.itextpdf.text.DocumentException;
import com.root.rentalheive.entities.Devis;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Date;
import java.util.List;

@Service
public interface DevisService {

    List<Devis> getDevis();
    Devis saveDevis(Date date, float price, Long demand_id);
    void sendDevis(Long id);
    ResponseEntity<FileSystemResource> sendAgreementWithEmail(Devis devis) throws DocumentException, IOException;
    void deleteDevis(Long id);
    Devis declineDevis(Long id);
    Devis getDevisById(Long id);

    List<Devis> getDevisByUser(Long id);




}
