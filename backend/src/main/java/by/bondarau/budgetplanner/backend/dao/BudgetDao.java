package by.bondarau.budgetplanner.backend.dao;

import by.bondarau.budgetplanner.backend.entity.Budget;

import java.util.List;

public interface BudgetDao extends CrudDao<Budget>{
    List<Budget> findByUserId(Long userId);
}
