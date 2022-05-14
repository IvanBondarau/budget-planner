package by.malinovskaya.budgetplanner.backend.service;

import by.malinovskaya.budgetplanner.backend.dto.BudgetDto;
import by.malinovskaya.budgetplanner.backend.dto.BudgetCreateDto;
import by.malinovskaya.budgetplanner.backend.dto.BudgetInfoDto;
import by.malinovskaya.budgetplanner.backend.dto.BudgetSearchDto;

import java.util.List;

public interface BudgetService {
    List<BudgetDto> findBudget(BudgetSearchDto dto);
    BudgetDto create(BudgetCreateDto dto);
    void delete(Long id);
    BudgetInfoDto read(Long id);
    void update(BudgetInfoDto infoDto, Long id);
}
