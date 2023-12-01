package com.root.rentalheive.services.interfaces;

import com.root.rentalheive.entities.User;

import java.util.Optional;

public interface UserService {
    User getUserById(Long id);
    Optional<User> findUserById(Long id);
    User addUser(User user);
}
