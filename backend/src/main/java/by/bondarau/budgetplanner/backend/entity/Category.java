package by.bondarau.budgetplanner.backend.entity;

public class Category {
    private Long id;
    private Long budgetId;
    private String name;
    private String color;
    private Long typeId;

    public Category() {
    }

    public Category(Long budgetId, String name, String color, Long typeId) {
        this.budgetId = budgetId;
        this.name = name;
        this.color = color;
        this.typeId = typeId;
    }

    public Category(Long id, Long budgetId, String name, String color, Long typeId) {
        this.id = id;
        this.budgetId = budgetId;
        this.name = name;
        this.color = color;
        this.typeId = typeId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getBudgetId() {
        return budgetId;
    }

    public void setBudgetId(Long budgetId) {
        this.budgetId = budgetId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Long getTypeId() {
        return typeId;
    }

    public void setTypeId(Long typeId) {
        this.typeId = typeId;
    }
}
