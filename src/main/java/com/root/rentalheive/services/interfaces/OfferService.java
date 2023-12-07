package com.root.rentalheive.services.interfaces;

import com.root.rentalheive.entities.Offer;

import java.util.List;

public interface OfferService {
    List<Offer> getOffers();
    Offer getOfferById(Long id);
    Offer saveOffer(Long devis_id);
    void sendOffer(Long id);
    void deleteOffer(Long id);
}
