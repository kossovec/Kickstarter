package ua.goit.dao;

import java.util.List;

public interface GenericDAO<T> {
   void add(T entity);
   T getById(Integer id);
   List<T> getAll();
   void update(T entity);
   void remove(Integer id);
}