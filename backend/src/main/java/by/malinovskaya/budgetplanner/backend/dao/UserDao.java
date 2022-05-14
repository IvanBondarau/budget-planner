package by.malinovskaya.budgetplanner.backend.dao;

import by.malinovskaya.budgetplanner.backend.entity.User;

import java.util.Optional;

public interface UserDao extends CrudDao<User> {

    Optional<User> login(String name, String password);

    String getRole(Long userId);

    void setRole(Long userId, String role);

}
