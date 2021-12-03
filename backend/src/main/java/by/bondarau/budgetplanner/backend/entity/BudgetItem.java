package by.bondarau.budgetplanner.backend.entity;

import java.math.BigDecimal;

public class BudgetItem {

    private Long id;
    private Long categoryId;
    private String name;
    private BigDecimal value;

    public BudgetItem() {
    }

    public BudgetItem(Long categoryId, String name, BigDecimal value) {
        this.categoryId = categoryId;
        this.name = name;
        this.value = value;
    }

    public BudgetItem(Long id, Long categoryId, String name, BigDecimal value) {
        this.id = id;
        this.categoryId = categoryId;
        this.name = name;
        this.value = value;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

}
