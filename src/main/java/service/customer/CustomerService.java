package service.customer;

import model.Customer;
import service.IService;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class CustomerService implements IService<Customer> {
    private static final List<Customer> customers;
    private static long autoIncreaseId = 0;

    static {
        customers = new ArrayList<>();
        customers.add(new Customer(autoIncreaseId++, "T", "t@codegym.vn", "Da Nang"));
        customers.add(new Customer(autoIncreaseId++, "Nhat", "nhat@codegym.vn", "Quang Tri"));
        customers.add(new Customer(autoIncreaseId++, "Trang", "trang@codegym.vn", "Ha Noi"));
        customers.add(new Customer(autoIncreaseId++, "Nguyen Binh Son", "son@codegym.vn", "Sai Gon"));
        customers.add(new Customer(autoIncreaseId++, "Dang Xuan Hoa", "hoa.dang@codegym.vn", "Da Nang"));
    }

    @Override
    public List<Customer> findAll() {
        return customers;
    }

    @Override
    public Customer findOne(Long id) {
        return customers.get(Math.toIntExact(id));
    }

    @Override
    public boolean save(Customer customer) {
        if (customer.getId() != null) {
            if (customers.remove(Math.toIntExact(customer.getId())) != null) {
                customers.add(Math.toIntExact(customer.getId()), customer);
                return true;
            }
            return false;
        } else {
            customer.setId(autoIncreaseId++);
            return customers.add(customer);
        }
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
        return customers.get(Math.toIntExact(id)) != null;
    }

    @Override
    public List<Customer> findAll(List<Long> ids) {
        List<Customer> filterList = new LinkedList<>();
        for (Long id : ids) {
            filterList.add(customers.get(Math.toIntExact(id)));
        }
        return filterList;
    }

    @Override
    public int count() {
        return customers.size();
    }

    @Override
    public boolean delete(Long id) {
        return customers.remove(Math.toIntExact(id)) != null;
    }

    @Override
    public boolean delete(Customer customer) {
        return customers.remove(customer);
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
        return customers.removeAll(findAll());
    }
}
