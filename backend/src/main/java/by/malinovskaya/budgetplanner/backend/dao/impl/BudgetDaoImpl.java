package by.malinovskaya.budgetplanner.backend.dao.impl;

import by.malinovskaya.budgetplanner.backend.dao.BudgetDao;
import by.malinovskaya.budgetplanner.backend.entity.Budget;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class BudgetDaoImpl implements BudgetDao {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    public BudgetDaoImpl(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Budget read(Long id) {
        return jdbcTemplate.query(
                "select * from budget where id = :id",
                new MapSqlParameterSource(Map.of("id", id)),
                new BeanPropertyRowMapper<>(Budget.class)
        ).get(0);
    }

    @Override
    public Long create(Budget entity) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(
                "insert into budget(user_id, name, creation_date) values(:userId, :name, :creationDate)",
                new BeanPropertySqlParameterSource(entity), keyHolder);
        return keyHolder.getKey().longValue();
    }

    @Override
    public void update(Budget entity) {

    }

    @Override
    public void delete(Long id) {
        int rows = jdbcTemplate.update("delete from budget where id = :id", Map.of("id", id));
        if (rows != 1) {
            throw new RuntimeException("Invalid delete");
        }
    }

    @Override
    public List<Budget> findAll() {
        return null;
    }

    @Override
    public List<Budget> findByUserId(Long userId) {
        return jdbcTemplate.query(
                "select * from budget where user_id = :userId",
                new MapSqlParameterSource(Map.of("userId", userId)),
                new BeanPropertyRowMapper<>(Budget.class)
        );
    }
}
