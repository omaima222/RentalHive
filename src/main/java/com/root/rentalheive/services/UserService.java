package com.root.rentalheive.services;

import com.root.rentalheive.entities.User;
import com.root.rentalheive.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    public Optional<User> findUserById(Long id) {
       return userRepository.findById(id);
    }


    public User addUser(User user) {
        return userRepository.save(user);
    }
}