package ua.goit.service;

import java.util.List;

public interface GenericService<T> {
    void add(T entity);
    T getById(Integer id);
    List<T> getAll();
    void update(T entity);
    void remove(Integer id);
}
