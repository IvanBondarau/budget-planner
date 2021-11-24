package by.bondarau.budgetplanner.backend.dao.user.impl;

import by.bondarau.budgetplanner.backend.dao.user.UserDao;
import by.bondarau.budgetplanner.backend.entity.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

@Repository
public class UserDaoImpl implements UserDao {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    public UserDaoImpl(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public User read(Long id) {
        Map<String, Object> params = Map.of("id", id);
        return jdbcTemplate.queryForObject("select * from user where id = :id", params, User.class);
    }

    @Override
    public Long save(User user) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        Map<String, Object> params = new ObjectMapper().convertValue(user, Map.class);
        SqlParameterSource parameterSource = new MapSqlParameterSource(params);
        jdbcTemplate.update("insert into user(username, email, password) values(:username, :email, :password)", parameterSource, keyHolder);
        return keyHolder.getKey().longValue();
    }

    @Override
    public void update(User user) {
        Map<String, Object> params = new ObjectMapper().convertValue(user, Map.class);
        SqlParameterSource parameterSource = new MapSqlParameterSource(params);
        jdbcTemplate.update("update user set username = :username, email = :email, password = :password where id = :id", parameterSource);
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
