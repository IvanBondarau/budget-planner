package by.bondarau.budgetplanner.backend.service.impl;

import by.bondarau.budgetplanner.backend.dao.UserDao;
import by.bondarau.budgetplanner.backend.dto.LoginDto;
import by.bondarau.budgetplanner.backend.dto.RegisterDto;
import by.bondarau.budgetplanner.backend.dto.UserDto;
import by.bondarau.budgetplanner.backend.entity.User;
import by.bondarau.budgetplanner.backend.exception.BusinessException;
import by.bondarau.budgetplanner.backend.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

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
        return mapper.map(user, UserDto.class);
    }

    @Override
    public UserDto register(RegisterDto registerDto) {
        User user = mapper.map(registerDto, User.class);
        Long id = userDao.create(user);
        user.setId(id);
        return mapper.map(user, UserDto.class);
    }

    @Override
    public UserDto updateUser(Long id, UserDto userDto) {
        User user = mapper.map(userDto, User.class);
        user.setId(id);
        userDao.update(user);
        return mapper.map(user, UserDto.class);
    }
}
