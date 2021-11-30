package by.bondarau.budgetplanner.backend.rest;

import by.bondarau.budgetplanner.backend.dto.BudgetCreateDto;
import by.bondarau.budgetplanner.backend.dto.BudgetDto;
import by.bondarau.budgetplanner.backend.dto.BudgetSearchDto;
import by.bondarau.budgetplanner.backend.service.BudgetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/budget")
public class BudgetController {
    private final BudgetService budgetService;

    @Autowired
    public BudgetController(BudgetService budgetService) {
        this.budgetService = budgetService;
    }

    @PostMapping("/search")
    public List<BudgetDto> search(@RequestBody BudgetSearchDto dto) {
        return budgetService.findBudget(dto);
    }

    @PostMapping
    public BudgetDto create(@RequestBody BudgetCreateDto dto) {
        return budgetService.create(dto);
    }
}
