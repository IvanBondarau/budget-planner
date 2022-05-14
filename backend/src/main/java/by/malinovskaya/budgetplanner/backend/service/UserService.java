package by.malinovskaya.budgetplanner.backend.service;

import by.malinovskaya.budgetplanner.backend.dto.LoginDto;
import by.malinovskaya.budgetplanner.backend.dto.RegisterDto;
import by.malinovskaya.budgetplanner.backend.dto.UserDto;

import java.util.List;

public interface UserService {
    UserDto login(LoginDto loginDto);
    UserDto register(RegisterDto registerDto);
    UserDto updateUser(Long id, UserDto userDto);
    void delete(Long id);
    List<UserDto> getAll();
}
