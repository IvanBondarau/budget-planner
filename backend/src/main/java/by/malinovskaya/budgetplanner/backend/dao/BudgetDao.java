package by.malinovskaya.budgetplanner.backend.dao;

import by.malinovskaya.budgetplanner.backend.entity.Budget;

import java.util.List;

public interface BudgetDao extends CrudDao<Budget>{
    List<Budget> findByUserId(Long userId);
}
