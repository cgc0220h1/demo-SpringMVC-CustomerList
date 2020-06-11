package service.customer;

import model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import repository.CustomerRepository;
import service.exception.DuplicateException;
import service.exception.NotFoundException;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImp implements CustomerService {
    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerServiceImp(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public Customer save(Customer customer) throws DuplicateException {
        try {
            return customerRepository.save(customer);
        } catch (DataIntegrityViolationException ex) {
            throw new DuplicateException();
        }
    }

    @Override
    public List<Customer> saveAll(List<Customer> customers) {
        List<Customer> customerSaved = new LinkedList<>();
        for (Customer customer : customers) {
            customerSaved.add(customerRepository.save(customer));
        }
        return customerSaved;
    }

    @Override
    public Page<Customer> findAll(Pageable pageable) {
        return customerRepository.findAll(pageable);
    }

    @Override
    public Page<Customer> findByNameContaining(String name, Pageable pageable) {
        return customerRepository.findAllByNameContaining(name, pageable);
    }

    @Override
    public List<Customer> findAllByProvince(String city) {
        return null;
    }

    @Override
    public Customer findById(Long id) throws NotFoundException {
        Optional<Customer> optionalCustomer = customerRepository.findById(id);
        if (!optionalCustomer.isPresent()) {
            throw new NotFoundException();
        }
        return optionalCustomer.get();
    }

    @Override
    public boolean existsById(Long id) {
        return customerRepository.existsById(id);
    }

    @Override
    public List<Customer> findAll() {
        List<Customer> customerList = new LinkedList<>();
        Iterable<Customer> iterable = customerRepository.findAll();
        for (Customer customer : iterable) {
            customerList.add(customer);
        }
        return customerList;
    }

    @Override
    public List<Customer> findAllById(List<Long> ids) {
        List<Customer> customerList = new LinkedList<>();
        for (long id : ids) {
            customerList.add(customerRepository.findById(id).orElse(null));
        }
        return customerList;
    }

    @Override
    public long count() {
        return customerRepository.count();
    }

    @Override
    public void deleteById(Long id) {
        customerRepository.deleteById(id);
    }

    @Override
    public void delete(Customer customer) {
        customerRepository.delete(customer);
    }

    @Override
    public void deleteAll(List<Customer> models) {

    }

    @Override
    public void deleteAll() {

    }
}
