package by.bondarau.budgetplanner.backend.service.impl;

import by.bondarau.budgetplanner.backend.dao.BudgetDao;
import by.bondarau.budgetplanner.backend.dto.BudgetDto;
import by.bondarau.budgetplanner.backend.dto.BudgetCreateDto;
import by.bondarau.budgetplanner.backend.dto.BudgetSearchDto;
import by.bondarau.budgetplanner.backend.entity.Budget;
import by.bondarau.budgetplanner.backend.service.BudgetService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BudgetServiceImpl implements BudgetService {

    private final BudgetDao dao;
    private final ModelMapper mapper;

    @Autowired
    public BudgetServiceImpl(BudgetDao dao, ModelMapper mapper) {
        this.dao = dao;
        this.mapper = mapper;
    }

    @Override
    public List<BudgetDto> findBudget(BudgetSearchDto dto) {
        return dao.findByUserId(dto.getUserId()).stream().map(
            t -> mapper.map(t, BudgetDto.class)
        ).collect(Collectors.toList());
    }

    @Override
    public BudgetDto create(BudgetCreateDto dto) {
        Budget budget = mapper.map(dto, Budget.class);
        Long id = dao.create(budget);
        budget.setId(id);
        return mapper.map(budget, BudgetDto.class);
    }
}
