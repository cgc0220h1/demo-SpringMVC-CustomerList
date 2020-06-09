package controllers;

import model.Customer;
import model.Province;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import org.springframework.data.domain.Pageable;
import service.customer.CustomerService;
import service.province.ProvinceService;

import java.util.List;
import java.util.Optional;

@RequestMapping("/")
@Controller
public class CustomerController {
    private final CustomerService customerService;

    private final ProvinceService provinceService;

    @Autowired
    public CustomerController(CustomerService customerService,
                              ProvinceService provinceService) {
        this.customerService = customerService;
        this.provinceService = provinceService;
    }

    @ModelAttribute
    public List<Province> provinceList() {
        return provinceService.findAll();
    }

    @GetMapping()
    public RedirectView redirect() {
        return new RedirectView("/customers?page=1?size=10&sort=name");
    }

    @GetMapping("/customers")
    public ModelAndView showCustomerList(Pageable pageable) throws Exception {
        Page<Customer> customers = customerService.findAll(pageable);
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
    public RedirectView updateCustomer(@ModelAttribute Customer customer) {
        customerService.save(customer);
        return redirect();
    }

    @GetMapping("/create")
    public ModelAndView showCreateCustomerForm() {
        ModelAndView modelAndView = new ModelAndView("customer/form");
        modelAndView.addObject("customer", new Customer());
        modelAndView.addObject("buttonLabel", "Tạo mới");
        return modelAndView;
    }

    @PostMapping("/create")
    public RedirectView addCustomer(@ModelAttribute("customer") Customer customer) {
        customerService.save(customer);
        return redirect();
    }

    @GetMapping("/search")
    public ModelAndView showResult(@RequestParam("search") Optional<String> search, Pageable pageable) throws Exception {
        Page<Customer> customers;
        ModelAndView modelAndView = new ModelAndView("customer/list");
        if (search.isPresent()) {
            customers = customerService.findByNameContaining(search.get(), pageable);
        } else {
            customers = customerService.findAll(pageable);
        }
        modelAndView.addObject("customers", customers);
        return modelAndView;
    }
}
