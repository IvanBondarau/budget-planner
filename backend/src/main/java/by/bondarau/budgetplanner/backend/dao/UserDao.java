package by.bondarau.budgetplanner.backend.dao;

import by.bondarau.budgetplanner.backend.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserDao extends CrudDao<User> {

    Optional<User> login(String name, String password);

}
