package by.bondarau.budgetplanner.backend.service;

import by.bondarau.budgetplanner.backend.dto.LoginDto;
import by.bondarau.budgetplanner.backend.dto.RegisterDto;
import by.bondarau.budgetplanner.backend.dto.UserDto;

public interface UserService {
    UserDto login(LoginDto loginDto);
    UserDto register(RegisterDto registerDto);
    UserDto updateUser(Long id, UserDto userDto);
}
