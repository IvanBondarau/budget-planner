package by.bondarau.budgetplanner.backend.dao.user;

import by.bondarau.budgetplanner.backend.entity.User;

import java.util.List;

public interface UserDao {

    User read(Long id);
    Long save(User user);
    List<User> findAll();
    void update(User user);
    void delete(Long id);
}
