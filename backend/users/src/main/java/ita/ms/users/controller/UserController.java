package ita.ms.users.controller;

import ita.ms.users.model.LoginDto;
import ita.ms.users.model.RegisterDto;
import ita.ms.users.model.User;
import ita.ms.users.service.UserService;
import ita.ms.users.utils.JwtUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/users")
public class UserController {

    @Autowired
    private JwtUtil jwtUtil;

    private static final Logger logger = LogManager.getLogger(UserController.class);

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    @ResponseBody
    public List<User> getAll() {
        try {
            logger.info("Getting all users...");
            return userService.getAll();
        } catch (Exception e) {
            logger.error("Error getting all users...");
            throw e;
        }
    }

    @PostMapping("/")
    @ResponseBody
    public ResponseEntity<String> addNewUser(RegisterDto dto) {
        try {
            logger.info("Creating an user...");
            Long newId = userService.createUser(dto);
            String token = jwtUtil.generateToken(newId.toString());
            return ResponseEntity.ok(token);
        } catch (Exception e) {
            logger.error("Error creating an user...");
            throw e;
        }
    }

    @PostMapping("/authenticate")
    @ResponseBody
    public ResponseEntity<String> authenticateUser(@RequestBody LoginDto dto) {
        try {
            logger.info("Authenticating user " + dto.getEmail());
            if (userService.authenticateUser(dto)) {
                String token = jwtUtil.generateToken(dto.getEmail());
                return ResponseEntity.ok(token);
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
            }
        } catch (Exception e) {
            logger.error("Error authenticating user " + dto.getEmail());
            throw e;
        }
    }

    @GetMapping("/test")
    public String test() {
        return "test users";
    }
}
