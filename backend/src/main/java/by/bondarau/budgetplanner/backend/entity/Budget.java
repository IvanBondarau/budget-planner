package by.bondarau.budgetplanner.backend.entity;

import java.util.Date;

public class Budget {
    private Long id;
    private Long userId;
    private String name;
    private Date creationDate;

    public Budget(Long userId, String name, Date creationDate) {
        this.userId = userId;
        this.name = name;
        this.creationDate = creationDate;
    }

    public Budget(Long id, Long userId, String name, Date creationDate) {
        this.id = id;
        this.userId = userId;
        this.name = name;
        this.creationDate = creationDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }
}
