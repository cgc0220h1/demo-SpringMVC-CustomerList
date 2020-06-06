package controllers;

import model.Customer;
import model.Province;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import service.IService;

import java.util.List;

@RequestMapping("/")
@Controller
public class CustomerController {
    private final IService<Customer> customerService;

    private final IService<Province> provinceService;

    @Autowired
    public CustomerController(IService<Customer> customerService,
                              IService<Province> provinceService) {
        this.customerService = customerService;
        this.provinceService = provinceService;
    }

    @ModelAttribute
    public List<Province> provinceList() {
        return provinceService.findAll();
    }

    @GetMapping()
    public ModelAndView showCustomerList() {
        List<Customer> customers = customerService.findAll();
        ModelAndView modelAndView = new ModelAndView("customer/list");
        modelAndView.addObject("customers", customers);
        return modelAndView;
    }

    @GetMapping("/customers/{id}")
    public ModelAndView showCustomerDetail(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView("customer/form");
        Customer customer = customerService.findById(id);
        modelAndView.addObject("customer", customer);
        modelAndView.addObject("buttonLabel", "Cập nhật");
        return modelAndView;
    }

    @PostMapping("/customers/{id}")
    public ModelAndView updateCustomer(@ModelAttribute Customer customer) {
        customerService.save(customer);
        return showCustomerList();
    }

    @GetMapping("/create")
    public ModelAndView showCreateCustomerForm() {
        ModelAndView modelAndView = new ModelAndView("customer/form");
        modelAndView.addObject("customer", new Customer());
        modelAndView.addObject("buttonLabel", "Tạo mới");
        return modelAndView;
    }

    @PostMapping("/create")
    public ModelAndView addCustomer(@ModelAttribute("customer") Customer customer) {
        customerService.save(customer);
        return showCustomerList();
    }
}
