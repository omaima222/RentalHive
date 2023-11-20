package com.root.rentalheive.services;

import com.root.rentalheive.entities.User;
import com.root.rentalheive.repositories.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    UserRepository userRepository;
    UserService(UserRepository userRepository){
        this.userRepository=userRepository;
    }
    public User getUserById(Long id){
        return userRepository.findUserById(id);
    }
}
