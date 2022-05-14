package by.malinovskaya.budgetplanner.backend.entity;

public class CategoryType {
    private long id;
    private String name;

    public CategoryType() {}

    public CategoryType(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
