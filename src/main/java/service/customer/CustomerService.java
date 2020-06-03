package service.customer;

import model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import repository.IRepository;
import service.IService;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class CustomerService implements IService<Customer> {
    @Autowired
    private IRepository<Customer> customerRepository;

    @Override
    public List<Customer> findAll() {
        return customerRepository.findAll();
    }

    @Override
    public Customer findOne(Long id) {
        return customerRepository.findOne(id);
    }

    @Override
    public boolean save(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public boolean save(List<Customer> customers) {
        for (Customer customer : customers) {
            if (!save(customer)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean exists(Long id) {
        return customerRepository.findOne(id) != null;
    }

    @Override
    public List<Customer> findAll(List<Long> ids) {
        List<Customer> filterList = new LinkedList<>();
        for (Long id : ids) {
            filterList.add(customerRepository.findOne(id));
        }
        return filterList;
    }

    @Override
    public int count() {
        return (int) customerRepository.count();
    }

    @Override
    public boolean delete(Long id) {
        return customerRepository.delete(id);
    }

    @Override
    public boolean delete(Customer customer) {
        return customerRepository.delete(customer);
    }

    @Override
    public boolean delete(List<Customer> customers) {
        for (Customer customer : customers) {
            if (!customers.remove(customer)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean deleteAll() {
        return customerRepository.delete(customerRepository.findAll());
    }
}
