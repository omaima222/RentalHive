package com.root.rentalheive.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class EquipmentReserved extends RuntimeException {
    public EquipmentReserved(String errorMessage) {
        super(errorMessage);
    }
}
