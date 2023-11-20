package com.root.rentalheive.services;

import com.root.rentalheive.repositories.OfferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OffreService {
    OfferRepository offerRepository;
    @Autowired
    public OffreService(OfferRepository offerRepository){this.offerRepository = offerRepository;}
}
