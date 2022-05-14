package by.malinovskaya.budgetplanner.backend.dao.impl;

import by.malinovskaya.budgetplanner.backend.dao.BudgetItemDao;
import by.malinovskaya.budgetplanner.backend.entity.BudgetItem;
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
public class BudgetItemDaoImpl implements BudgetItemDao {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    public BudgetItemDaoImpl(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<BudgetItem> findByCategoryId(Long id) {
        return jdbcTemplate.query(
                "select * from item where category_id = :categoryId",
                new MapSqlParameterSource(Map.of("categoryId", id)),
                new BeanPropertyRowMapper<>(BudgetItem.class)
        );
    }

    @Override
    public Long create(BudgetItem item) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(
                "insert into item(category_id, name, value) VALUES(:categoryId, :name, :value)",
                new BeanPropertySqlParameterSource(item),
                keyHolder
        );
        return ((BigInteger)keyHolder.getKey()).longValue();
    }


}
