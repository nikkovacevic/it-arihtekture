package ita.ms.users.controller;

import ita.ms.users.model.LoginDto;
import ita.ms.users.model.RegisterDto;
import ita.ms.users.model.User;
import ita.ms.users.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    @ResponseBody
    public List<User> getAll() {
        try {
            return userService.getAll();
        } catch (Exception e) {
            throw e;
        }
    }

    @PostMapping("/")
    @ResponseBody
    public Long addNewUser(RegisterDto dto) {
        try {
            return userService.createUser(dto);
        } catch (Exception e) {
            throw e;
        }
    }

    @PostMapping("/authenticate")
    @ResponseBody
    public boolean authenticateUser(LoginDto dto) {
        try {
            return userService.authenticateUser(dto);
        } catch (Exception e) {
            throw e;
        }
    }
}
