package controllers;

import model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import service.IService;

import java.util.List;

@RequestMapping("/")
@Controller
public class CustomerController {
    @Autowired
    private IService<Customer> iService;

    @GetMapping()
    public ModelAndView showList() {
        List<Customer> customers = iService.findAll();
        ModelAndView modelAndView = new ModelAndView("list");
        modelAndView.addObject("customers", customers);
        return modelAndView;
    }

    @GetMapping("/customers/{id}")
    public ModelAndView showDetail(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView("info");
        Customer customer = iService.findOne(id);
        modelAndView.addObject("customer", customer);
        modelAndView.addObject("buttonLabel", "Cập nhật");
        return modelAndView;
    }

    @PostMapping("/customers/{id}")
    public ModelAndView updateCustomer(@ModelAttribute Customer customer) {
        iService.save(customer);
        return showList();
    }

    @GetMapping("/create")
    public ModelAndView showCreateForm() {
        ModelAndView modelAndView = new ModelAndView("info");
        modelAndView.addObject("customer", new Customer());
        modelAndView.addObject("buttonLabel", "Tạo mới");
        return modelAndView;
    }

    @PostMapping("/create")
    public ModelAndView addCustomer(@ModelAttribute("customer") Customer customer) {
        iService.save(customer);
        return showList();
    }
}
