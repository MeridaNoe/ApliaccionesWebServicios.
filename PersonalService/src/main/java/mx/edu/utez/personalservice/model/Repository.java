package mx.edu.utez.personalservice.model;

import java.util.List;

public interface Repository<T> {
    List<T> findAll();
    T findOne(Long id);
    boolean save(T object);
    boolean update(T object);
    boolean delete(Long id);
}
