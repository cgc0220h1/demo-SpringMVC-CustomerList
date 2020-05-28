package controllers;

import model.Customer;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import service.CustomerService;
import service.CustomerServiceFactory;

import java.util.List;

@RequestMapping("/customers")
@Controller
public class CustomerController {
    private CustomerService customerService = CustomerServiceFactory.getInstance();

    @GetMapping("/view")
    public ModelAndView showList() {
        List<Customer> customers = customerService.findAll();
        ModelAndView modelAndView = new ModelAndView("list");
        modelAndView.addObject("customers", customers);
        return modelAndView;
    }

    @GetMapping("/{id}")
    public ModelAndView showDetail(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView("info");
        Customer customer = customerService.findOne(id);
        modelAndView.addObject("customer", customer);
        return modelAndView;
    }

    @PostMapping("/{id}")
    public ModelAndView updateCustomer(@ModelAttribute Customer customer) {
        Customer customer1 = customerService.save(customer);
        return showDetail(customer1.getId());
    }
}
