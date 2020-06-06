package repository;

import model.Customer;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class IRepositoryTest {
    private static IRepository<Customer> repository;
    private static List<Customer> customers;
    private  static long autoIncreaseId = 0;

    @BeforeAll
    static void init() {
        repository = new CustomerRepository();
        customers = new ArrayList<>();
        customers.add(new Customer(autoIncreaseId++, "T", "t@codegym.vn", "Da Nang"));
        customers.add(new Customer(autoIncreaseId++, "Nhat", "nhat@codegym.vn", "Quang Tri"));
        customers.add(new Customer(autoIncreaseId++, "Trang", "trang@codegym.vn", "Ha Noi"));
        customers.add(new Customer(autoIncreaseId++, "Nguyen Binh Son", "son@codegym.vn", "Sai Gon"));
        customers.add(new Customer(autoIncreaseId++, "Dang Xuan Hoa", "hoa.dang@codegym.vn", "Da Nang"));
    }

    @Test
    void findAll() {
        System.out.println(repository.findAll());
    }

    @Test
    void findOne() {
    }

    @Test
    void save() {
    }

    @Test
    void testSave() {
    }

    @Test
    void exists() {
    }

    @Test
    void testFindAll() {
    }

    @Test
    void count() {
    }

    @Test
    void delete() {
    }

    @Test
    void testDelete() {
    }

    @Test
    void testDelete1() {
    }

    @Test
    void deleteAll() {
    }
}