package com.root.rentalheive.services;

import com.root.rentalheive.entities.Devis;
import com.root.rentalheive.entities.Offer;
import com.root.rentalheive.enums.DevisStatus;
import com.root.rentalheive.repositories.DevisRepository;
import com.root.rentalheive.repositories.OfferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import com.root.rentalheive.services.interfaces.OfferService;

import java.util.List;

@Service
public class OfferServiceImpl  implements OfferService {
    @Autowired
    private JavaMailSender mailSender;
    OfferRepository offerRepository;

    DevisRepository devisRepository;
    @Autowired
    public  OfferServiceImpl(OfferRepository offerRepository, DevisRepository devisRepository){
        this.offerRepository = offerRepository;
        this.devisRepository = devisRepository;
    }

    public List<Offer> getOffers(){return this.offerRepository.findAll();}
    public Offer getOfferById(Long id){
        if(this.offerRepository.findById(id).isPresent()){
            return this.offerRepository.findById(id).get();
        }
        return null;
    }

    public Offer saveOffer(Long devis_id){
        Devis devis = this.devisRepository.findById(devis_id).get();
        if(devis.getStatus()== DevisStatus.APPROVED){
            Offer offer = new Offer();
            offer.setDevis(devis);
            return this.offerRepository.save(offer);
        }
        return null;
    }


    public void sendOffer(Long id){
        Offer offer = this.offerRepository.findById(id).get();

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo("user@gmail.com");
        message.setFrom("RentalHive@gmail.com");
        message.setSubject("Final offer");
        message.setText(offer.toMap().toString());

        mailSender.send(message);
    }

    public  void deleteOffer(Long id){
        Offer offer = this.offerRepository.findById(id).get();
        this.offerRepository.delete(offer);
    }

}
