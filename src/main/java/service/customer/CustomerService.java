package service.customer;

import model.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import service.IService;

public interface CustomerService extends IService<Customer> {
    Page<Customer> findByNameContaining(String name, Pageable pageable);
}
