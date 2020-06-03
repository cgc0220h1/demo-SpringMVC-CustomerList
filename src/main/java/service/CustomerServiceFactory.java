package service;


import service.customer.SimpleIServiceImpl;

public class CustomerServiceFactory {
    private static IService singleton;

    public static synchronized IService getInstance() {
        if (singleton == null) {
            singleton = new SimpleIServiceImpl();
        }
        return singleton;
    }
}
