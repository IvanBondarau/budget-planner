package by.malinovskaya.budgetplanner.backend.dto;

import java.util.List;

public class CategoryInfoDto {

    private Long id;
    private String name;
    private String color;
    private List<ItemInfoDto> items;
    private Long typeId;

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

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public List<ItemInfoDto> getItems() {
        return items;
    }

    public void setItems(List<ItemInfoDto> items) {
        this.items = items;
    }

    public Long getTypeId() {
        return typeId;
    }

    public void setTypeId(Long typeId) {
        this.typeId = typeId;
    }
}
