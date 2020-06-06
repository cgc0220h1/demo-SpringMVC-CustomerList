package service;

import model.Customer;

import java.util.List;
import java.util.Optional;

public interface IService<T> {
    T save(T entity);

    List<T> saveAll(List<T> entities);

    T findById(Long id);

    boolean existsById(Long id);

    List<T> findAll();

    List<T> findAllById(List<Long> ids);

    long count();

    void deleteById(Long id);

    void delete(T model);

    void deleteAll(List<T> models);

    void deleteAll();
}
