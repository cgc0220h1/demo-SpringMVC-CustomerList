package service.customer;

import model.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import service.IService;
import service.exception.DuplicateException;

import java.util.List;

public interface CustomerService extends IService<Customer> {
    Customer save(Customer entity) throws DuplicateException;

    Page<Customer> findByNameContaining(String name, Pageable pageable);

    List<Customer> findAllByProvince(String city);
}
