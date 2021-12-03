package by.bondarau.budgetplanner.backend.dao;

import by.bondarau.budgetplanner.backend.entity.BudgetItem;

import java.util.List;

public interface BudgetItemDao {
    List<BudgetItem> findByCategoryId(Long id);
    Long create(BudgetItem item);
}
