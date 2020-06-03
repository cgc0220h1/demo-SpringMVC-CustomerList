package repository;

import java.util.List;

public interface IRepository<T> {
    List<T> findAll();

    T findOne(Long id);

    boolean save(T customer);

    boolean save(List<T> customers);

    boolean exists(Long id);

    List<T> findAll(List<Long> ids);

    long count();

    boolean delete(Long id);

    boolean delete(T customer);

    boolean delete(List<T> customers);

    boolean deleteAll();
}
