package by.malinovskaya.budgetplanner.backend.rest;

import by.malinovskaya.budgetplanner.backend.dto.LoginDto;
import by.malinovskaya.budgetplanner.backend.dto.RegisterDto;
import by.malinovskaya.budgetplanner.backend.dto.UserDto;
import by.malinovskaya.budgetplanner.backend.service.UserService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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

    @PutMapping("/{id}")
    public UserDto updateUser(@PathVariable Long id,@RequestBody UserDto dto) {
        return userService.updateUser(id, dto);
    }

    @GetMapping
    public List<UserDto> getAll() {
        return userService.getAll();
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        this.userService.delete(id);
    }


}
