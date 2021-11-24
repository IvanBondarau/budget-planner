package by.bondarau.budgetplanner.backend.rest;

import by.bondarau.budgetplanner.backend.dao.user.UserDao;
import by.bondarau.budgetplanner.backend.entity.User;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

    private final UserDao userDao;

    public UserController(UserDao userDao) {
        this.userDao = userDao;
    }

    @GetMapping("/user/{id}")
    public User getUser(@PathVariable Long id) {
        return userDao.read(id);
    }

    @GetMapping("/user")
    public List<User> getAll() {
        return userDao.findAll();
    }

    @PostMapping("/user")
    public User save(@RequestBody User user) {
        Long id = userDao.save(user);
        user.setId(id);
        return user;
    }

    @PutMapping("/user/{id}")
    public User update(@RequestBody User user, @PathVariable Long id) {
        user.setId(id);
        userDao.update(user);
        return user;
    }

    @DeleteMapping("/user/{id}")
    public void delete(@PathVariable Long id) {
        userDao.delete(id);
    }
}
