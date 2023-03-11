package ita.ms.users.service;

import ita.ms.users.model.LoginDto;
import ita.ms.users.model.RegisterDto;
import ita.ms.users.model.User;
import ita.ms.users.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCrypt;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Test
    void testCreateUser() {
        RegisterDto dto = new RegisterDto(
                "test@email.com",
                "password",
                "Janez",
                "Novak"
        );

        User savedUser = new User(
                1L,
                "test@email.com",
                BCrypt.hashpw("password", BCrypt.gensalt()),
                "Janez",
                "Novak"
        );

        when(userRepository.save(any(User.class))).thenReturn(savedUser);

        Long id = userService.createUser(dto);

        assertEquals(1L, id);
    }

    @Test
    void testAuthenticateUserWithCorrectCredentials() {
        String hashedPassword = BCrypt.hashpw("password", BCrypt.gensalt());
        User testUser = new User(
                1L,
                "test@email.com",
                hashedPassword,
                "Janez",
                "Novak"
        );

        when(userRepository.findUserByEmail("test@email.com")).thenReturn(testUser);

        LoginDto dto = new LoginDto(
                "test@email.com",
                "password"
        );

        boolean isPasswordCorrect = userService.authenticateUser(dto);
        assertTrue(isPasswordCorrect);
    }

    @Test
    void testAuthenticateUserWithIncorrectEmail() {
        when(userRepository.findUserByEmail("test@email.com")).thenReturn(null);

        LoginDto dto = new LoginDto(
                "test@email.com",
                "password"
        );

        boolean doesEmailExist = userService.authenticateUser(dto);
        assertFalse(doesEmailExist);
    }

    @Test
    void testAuthenticateUserWithIncorrectPassword() {
        String hashedPassword = BCrypt.hashpw("password", BCrypt.gensalt());
        User testUser = new User(
                1L,
                "test@email.com",
                hashedPassword,
                "Janez",
                "Novak"
        );

        when(userRepository.findUserByEmail("test@email.com")).thenReturn(testUser);

        LoginDto dto = new LoginDto(
                "test@email.com",
                "wrongPassword"
        );

        boolean isPasswordCorrect = userService.authenticateUser(dto);
        assertFalse(isPasswordCorrect);

    }

    @Test
    void testGetAll() {
        List<User> testUsers = new ArrayList<>();
        testUsers.add(new User(
                1L,
                "test@email.com",
                "password",
                "Janez",
                "Novak"
        ));
        testUsers.add(new User(
                2L,
                "test2@email.com",
                "password2",
                "Cristiano",
                "Ronaldo"
        ));

        when(userRepository.findAll()).thenReturn(testUsers);

        List<User> allUsers = userService.getAll();
        assertEquals(testUsers, allUsers);
    }
}