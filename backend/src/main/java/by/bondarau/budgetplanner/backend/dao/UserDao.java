package by.bondarau.budgetplanner.backend.dao;

import by.bondarau.budgetplanner.backend.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserDao {

    Optional<User> login(String name, String password);
    User read(Long id);
    Long save(User user);
    List<User> findAll();
    void update(User user);
    void delete(Long id);
}
