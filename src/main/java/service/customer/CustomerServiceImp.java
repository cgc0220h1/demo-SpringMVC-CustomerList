package service.customer;

import model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.customer.ICustomerRepository;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImp implements ICustomerService {
    private final ICustomerRepository customerRepository;

    @Autowired
    public CustomerServiceImp(ICustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public Customer save(Customer customer) {
        return customerRepository.save(customer);
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
    public Customer findById(Long id) {
        Optional<Customer> optionalCustomer = customerRepository.findById(id);
        return optionalCustomer.orElse(null);
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
