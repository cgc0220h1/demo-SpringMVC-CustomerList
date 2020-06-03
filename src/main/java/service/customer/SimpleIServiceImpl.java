package service.customer;


import model.Customer;
import service.IService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Arrays.asList;

public class SimpleIServiceImpl implements IService<Customer> {
    private static List<Customer> customers;
    private static long autoIncreaseId = 0;

    static {
        customers = asList(
                new Customer(autoIncreaseId++, "T", "t@codegym.vn", "Da Nang"),
                new Customer(autoIncreaseId++, "Nhat", "nhat@codegym.vn", "Quang Tri"),
                new Customer(autoIncreaseId++, "Trang", "trang@codegym.vn", "Ha Noi"),
                new Customer(autoIncreaseId++, "Nguyen Binh Son", "son@codegym.vn", "Sai Gon"),
                new Customer(autoIncreaseId++, "Dang Xuan Hoa", "hoa.dang@codegym.vn", "Da Nang")
        );
    }

    @Override
    public List<Customer> findAll() {
        return null;
    }

    @Override
    public Customer findOne(Long id) {
        return null;
    }

    @Override
    public boolean save(Customer customer) {
        return false;
    }

    @Override
    public boolean save(List<Customer> customers) {
        return false;
    }

    @Override
    public boolean exists(Long id) {
        return false;
    }

    @Override
    public List<Customer> findAll(List<Long> ids) {
        return null;
    }

    @Override
    public int count() {
        return 0;
    }

    @Override
    public boolean delete(Long id) {
        return false;
    }

    @Override
    public boolean delete(Customer customer) {
        return false;
    }

    @Override
    public boolean delete(List<Customer> customers) {
        return false;
    }

    @Override
    public boolean deleteAll() {
        return false;
    }
}
