package by.malinovskaya.budgetplanner.backend.dao.impl;

import by.malinovskaya.budgetplanner.backend.dao.CategoryDao;
import by.malinovskaya.budgetplanner.backend.entity.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

@Repository
public class CategoryDaoImpl implements CategoryDao {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    public CategoryDaoImpl(NamedParameterJdbcTemplate template) {
        this.jdbcTemplate = template;
    }

    @Override
    public List<Category> findByBudgetId(Long id, Long typeId) {
        return jdbcTemplate.query(
                "select * from category where budget_id = :budgetId and type_id = :typeId",
                new MapSqlParameterSource(Map.of("budgetId", id, "typeId", typeId)),
                new BeanPropertyRowMapper<>(Category.class)
        );
    }

    @Override
    public Long create(Category category) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(
                "insert into category(budget_id, name, color, type_id) VALUES(:budgetId, :name, :color, :typeId)",
                new BeanPropertySqlParameterSource(category),
                keyHolder
        );
        return ((BigInteger)keyHolder.getKey()).longValue();
    }

    @Override
    public void deleteByBudgetId(Long budgetId) {
        jdbcTemplate.update(
                "delete from category where budget_id = :budgetId",
                Map.of("budgetId", budgetId)
        );
    }
}
