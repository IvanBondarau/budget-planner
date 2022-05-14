package by.malinovskaya.budgetplanner.backend.service.impl;

import by.malinovskaya.budgetplanner.backend.dao.UserDao;
import by.malinovskaya.budgetplanner.backend.dto.LoginDto;
import by.malinovskaya.budgetplanner.backend.dto.RegisterDto;
import by.malinovskaya.budgetplanner.backend.dto.UserDto;
import by.malinovskaya.budgetplanner.backend.entity.User;
import by.malinovskaya.budgetplanner.backend.exception.BusinessException;
import by.malinovskaya.budgetplanner.backend.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserDao userDao;
    private final ModelMapper mapper;

    public UserServiceImpl(UserDao userDao, ModelMapper modelMapper) {
        this.userDao = userDao;
        this.mapper = modelMapper;
    }

    @Override
    public UserDto login(LoginDto loginDto) {
        Optional<User> loginResult = userDao.login(loginDto.getName(), loginDto.getPassword());
        if (loginResult.isEmpty()) {
            throw new BusinessException("User not found");
        }
        User user = loginResult.get();
        UserDto dto = mapper.map(user, UserDto.class);
        String role = userDao.getRole(user.getId());
        dto.setRole(role);
        return dto;
    }

    @Override
    public UserDto register(RegisterDto registerDto) {
        User user = mapper.map(registerDto, User.class);
        Long id = userDao.create(user);
        String role = userDao.getRole(id);
        UserDto dto = mapper.map(user, UserDto.class);
        dto.setId(id);
        dto.setRole(role);
        return dto;
    }

    @Override
    public UserDto updateUser(Long id, UserDto userDto) {
        User user = mapper.map(userDto, User.class);
        user.setId(id);
        userDao.update(user);
        return mapper.map(user, UserDto.class);
    }

    public List<UserDto> getAll() {
        return userDao.findAll()
                .stream().map(
                        user -> mapper.map(user, UserDto.class)
                ).peek(
                        userDto -> {
                            String role = userDao.getRole(userDto.getId());
                            userDto.setRole(role);
                        }
                ).collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {
        this.userDao.delete(id);
    }
}
