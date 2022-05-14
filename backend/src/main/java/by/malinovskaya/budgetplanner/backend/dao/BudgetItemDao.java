package by.malinovskaya.budgetplanner.backend.dao;

import by.malinovskaya.budgetplanner.backend.entity.BudgetItem;

import java.util.List;

public interface BudgetItemDao {
    List<BudgetItem> findByCategoryId(Long id);
    Long create(BudgetItem item);
}
