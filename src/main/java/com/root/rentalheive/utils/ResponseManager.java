package com.root.rentalheive.utils;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ResponseManager {
    static public <T> ResponseEntity<T> create(Object data, HttpStatus status){
        ResponseEntity<T> responseEntity = new ResponseEntity<>( (T) data, status);
        return responseEntity;
    }
}
