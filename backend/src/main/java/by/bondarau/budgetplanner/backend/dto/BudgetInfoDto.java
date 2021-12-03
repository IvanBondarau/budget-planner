package by.bondarau.budgetplanner.backend.dto;

import java.util.List;

public class BudgetInfoDto {

    private Long id;
    private String name;
    private List<CategoryInfoDto> incomes;
    private List<CategoryInfoDto> expenses;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<CategoryInfoDto> getIncomes() {
        return incomes;
    }

    public void setIncomes(List<CategoryInfoDto> incomes) {
        this.incomes = incomes;
    }

    public List<CategoryInfoDto> getExpenses() {
        return expenses;
    }

    public void setExpenses(List<CategoryInfoDto> expenses) {
        this.expenses = expenses;
    }
}
