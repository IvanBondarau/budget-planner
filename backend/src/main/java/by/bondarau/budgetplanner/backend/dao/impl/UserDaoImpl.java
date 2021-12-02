package by.bondarau.budgetplanner.backend.dao.impl;

import by.bondarau.budgetplanner.backend.dao.UserDao;
import by.bondarau.budgetplanner.backend.entity.User;
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

    public UserDaoImpl(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Optional<User> login(String name, String password) {
        List<User> result = jdbcTemplate.query(
                "select * from user where (username = :name or email = :name) and (password = :password)",
                new MapSqlParameterSource(Map.of("name", name, "password", password)),
                new BeanPropertyRowMapper<>(User.class)
        );
        if (result.isEmpty()) {
            return Optional.empty();
        } else  {
            return Optional.of(result.get(0));
        }

    }

    @Override
    public Long create(User user) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        Map<String, Object> params = new ObjectMapper().convertValue(user, Map.class);
        SqlParameterSource parameterSource = new MapSqlParameterSource(params);
        jdbcTemplate.update("insert into user(username, email, password) values(:username, :email, :password)", parameterSource, keyHolder);
        return keyHolder.getKey().longValue();
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
        jdbcTemplate.update("delete from user where id = :id", Map.of("id", id));
    }

    @Override
    public List<User> findAll() {
        return jdbcTemplate.query("select id, username, email, password from user",
                new BeanPropertyRowMapper<>(User.class));
    }
}
