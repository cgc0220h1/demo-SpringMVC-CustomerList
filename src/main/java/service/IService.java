package service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import service.exception.DuplicateException;
import service.exception.NotFoundException;

import java.util.List;

public interface IService<T> {
    T save(T entity) throws DuplicateException;

    List<T> saveAll(List<T> entities);

    T findById(Long id) throws NotFoundException;

    boolean existsById(Long id);

    List<T> findAll();

    Page<T> findAll(Pageable pageable);

    List<T> findAllById(List<Long> ids);

    long count();

    void deleteById(Long id);

    void delete(T model);

    void deleteAll(List<T> models);

    void deleteAll();
}
