package by.malinovskaya.budgetplanner.backend.dao.impl;

import by.malinovskaya.budgetplanner.backend.dao.UserDao;
import by.malinovskaya.budgetplanner.backend.db.SaltGenerator;
import by.malinovskaya.budgetplanner.backend.entity.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class UserDaoImpl implements UserDao {

    private final NamedParameterJdbcTemplate jdbcTemplate;
    private final SaltGenerator saltGenerator;

    public UserDaoImpl(NamedParameterJdbcTemplate jdbcTemplate,
                       SaltGenerator saltGenerator) {
        this.jdbcTemplate = jdbcTemplate;
        this.saltGenerator = saltGenerator;
    }

    @Override
    public Optional<User> login(String name, String password) {
        List<User> result = jdbcTemplate.query(
                "select * from user where (username = :name or email = :name) and (password = sha2(concat(:password, salt), 512))",
                new MapSqlParameterSource(Map.of("name", name, "password", password)),
                new BeanPropertyRowMapper<>(User.class)
        );
        if (result.isEmpty()) {
            return Optional.empty();
        } else  {
            result.forEach(
                    user -> jdbcTemplate.update("insert into login_history(username) values (:username)", Map.of("username", user.getUsername()))
            );
            return Optional.of(result.get(0));
        }

    }

    @Override
    public Long create(User user) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        Map<String, Object> params = new ObjectMapper().convertValue(user, Map.class);
        params.put("salt", new String(saltGenerator.generateSalt()));
        SqlParameterSource parameterSource = new MapSqlParameterSource(params);
        jdbcTemplate.update("insert into user(username, email, password, salt) values(:username, :email, sha2(concat(:password, :salt), 512), :salt)", parameterSource, keyHolder);
        Long id = keyHolder.getKey().longValue();
        jdbcTemplate.update("insert into user_role(name, user_id) values(:name, :user_id)", Map.of(
                "name", "user",
                "user_id", id
        ));
        return id;
    }

    @Override
    public User read(Long id) {
        Map<String, Object> params = Map.of("id", id);
        return jdbcTemplate.query("select * from user where id = :id", new MapSqlParameterSource(params),
                new BeanPropertyRowMapper<>(User.class)).get(0);
    }

    @Override
    public void update(User user) {
        SqlParameterSource parameterSource = new BeanPropertySqlParameterSource(user);
        jdbcTemplate.update("update user set username = :username, email = :email where id = :id", parameterSource);
    }

    @Override
    public void delete(Long id) {
        jdbcTemplate.update("delete from user_role where user_id = :id", Map.of("id", id));
        jdbcTemplate.update("delete from user where id = :id", Map.of("id", id));
    }

    @Override
    public List<User> findAll() {
        List<User>  users = jdbcTemplate.query("select id, username, email, password from user",
                new BeanPropertyRowMapper<>(User.class));
        return users;
    }

    @Override
    public String getRole(Long userId) {
        return jdbcTemplate.queryForObject("select name from user_role where user_id = :user_id", Map.of("user_id", userId), String.class);
    }

    @Override
    public void setRole(Long userId, String role) {
        jdbcTemplate.update(
                "delete from user_role where user_id = :user_id", Map.of("user_id", userId)
        );
        jdbcTemplate.update(
                "insert into user_role(user_id, name) values(:user_id, :name)", Map.of(
                        "user_id", userId,
                            "name", role
                )
        );
    }
}
