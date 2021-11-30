package by.bondarau.budgetplanner.backend.service;

import by.bondarau.budgetplanner.backend.dto.BudgetDto;
import by.bondarau.budgetplanner.backend.dto.BudgetCreateDto;
import by.bondarau.budgetplanner.backend.dto.BudgetSearchDto;

import java.util.List;

public interface BudgetService {
    List<BudgetDto> findBudget(BudgetSearchDto dto);
    BudgetDto create(BudgetCreateDto dto);
}
