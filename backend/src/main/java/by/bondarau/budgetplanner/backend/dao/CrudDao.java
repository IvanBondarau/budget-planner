package by.bondarau.budgetplanner.backend.dao;

import java.util.List;

public interface CrudDao<T> {
    Long create(T entity);
    T read(Long id);
    void update(T entity);
    void delete(Long id);
    List<T> findAll();
}
