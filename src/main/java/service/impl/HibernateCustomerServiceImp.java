package service.impl;

import model.Customer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;
import service.CustomerService;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.Collections;
import java.util.List;
import java.util.Properties;

public class HibernateCustomerServiceImp implements CustomerService {
    private static SessionFactory sessionFactory;
    private static EntityManager entityManager;

    static {
        Configuration configuration = new Configuration();
        Properties settings = new Properties();
        settings.put(Environment.DRIVER, "com.mysql.cj.jdbc.Driver");
        settings.put(Environment.URL, "jdbc:mysql://localhost:3306/customer");
        settings.put(Environment.USER, "root");
        settings.put(Environment.PASS, "123456");
        settings.put(Environment.DIALECT, "org.hibernate.dialect.MySQL8Dialect");
        settings.put(Environment.SHOW_SQL, "true");
        settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
        settings.put(Environment.HBM2DDL_AUTO, "update");

        configuration.setProperties(settings);
        configuration.addAnnotatedClass(Customer.class);

        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                .applySettings(configuration.getProperties()).build();

        sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        entityManager = sessionFactory.createEntityManager();
    }

    @Override
    public List<Customer> findAll() {
        String queryStr = "SELECT c FROM Customer AS c";
        TypedQuery<Customer> query = entityManager.createQuery(queryStr, Customer.class);
        return query.getResultList();
    }

    @Override
    public Customer findOne(Long id) {
        String queryStr = "SELECT c FROM Customer AS c WHERE c.id = :id";
        TypedQuery<Customer> query = entityManager.createQuery(queryStr, Customer.class);
        query.setParameter("id", id);
        return query.getSingleResult();
    }

    @Override
    public Customer save(Customer customer) {
        Session session;
        Transaction transaction = null;
        session = sessionFactory.openSession();

        try {
            transaction = session.beginTransaction();
            if (customer.getId() == null) {
                session.save(customer);
            } else {
                session.update(customer);
            }
            transaction.commit();
            return customer;
        } catch (Exception e) {
            e.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            if (transaction == null) {
                session.close();
            }
        }
        return null;
    }

    @Override
    public List<Customer> save(List<Customer> customers) {
        return null;
    }

    @Override
    public boolean exists(Long id) {
        return findOne(id) != null;
    }

    @Override
    public List<Customer> findAll(List<Long> ids) {
        return Collections.emptyList();
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public void delete(Customer customer) {

    }

    @Override
    public void delete(List<Customer> customers) {

    }

    @Override
    public void deleteAll() {

    }
}
