package by.bondarau.budgetplanner.backend.dao;

import by.bondarau.budgetplanner.backend.entity.Category;

import java.util.List;

public interface CategoryDao {
    List<Category> findByBudgetId(Long id, Long typeId);
    Long create(Category category);
    void deleteByBudgetId(Long budgetId);
}
