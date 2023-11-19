package com.root.rentalheive;

import com.root.rentalheive.services.UserService;
import org.junit.jupiter.api.BeforeAll;

import static org.mockito.Mockito.mock;

public class UserServiceTest {

    @BeforeAll
    public static void init() throws Exception{
        userService = new UserService();
        userRepository = mock(UserRepositoryImpl.class);

    }
}
