package by.bondarau.budgetplanner.backend.rest;

import by.bondarau.budgetplanner.backend.dto.LoginDto;
import by.bondarau.budgetplanner.backend.dto.RegisterDto;
import by.bondarau.budgetplanner.backend.dto.UserDto;
import by.bondarau.budgetplanner.backend.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public UserDto register(@RequestBody RegisterDto dto) {
        return userService.register(dto);
    }

    @PostMapping("/login")
    public UserDto login(@RequestBody LoginDto dto) {
        return userService.login(dto);
    }


}
