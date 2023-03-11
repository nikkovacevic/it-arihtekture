package ita.ms.users.service;

import ita.ms.users.model.LoginDto;
import ita.ms.users.model.RegisterDto;
import ita.ms.users.model.User;
import ita.ms.users.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    public Long createUser(RegisterDto registerDto) {
        try {
            String hashedPassword = BCrypt.hashpw(registerDto.getPassword(), BCrypt.gensalt());
            User user = new User(
                    null,
                    registerDto.getEmail(),
                    hashedPassword,
                    registerDto.getFirstName(),
                    registerDto.getLastName()
            );
            User savedUser = userRepository.save(user);
            return savedUser.getId();
        } catch (Exception e) {
            throw e;
        }
    }

    public boolean authenticateUser(LoginDto loginDto) {
        try {
            User user = userRepository.findUserByEmail(loginDto.getEmail());
            if (user == null) {
                return false;
            }
            String hashedPassword = user.getPassword();
            boolean isPasswordCorrect = BCrypt.checkpw(loginDto.getPassword(), hashedPassword);
            return isPasswordCorrect;
        } catch (Exception e) {
            throw e;
        }
    }

    public List<User> getAll() {
        try {
            return userRepository.findAll();
        } catch (Exception e) {
            throw e;
        }
    }

}
