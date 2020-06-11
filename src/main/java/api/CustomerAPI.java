package api;

import model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import service.customer.CustomerService;
import service.exception.DuplicateException;
import service.exception.NotFoundException;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
public class CustomerAPI {
    private final CustomerService customerService;

    @Autowired
    public CustomerAPI(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    public ResponseEntity<List<Customer>> listAllCustomers() {
        List<Customer> customers = customerService.findAll();
        if (customers.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }

    @GetMapping("/?province")
    public ResponseEntity<List<Customer>> findAllCustomersByProvince(@RequestParam("province") String provinceName) {
        List<Customer> customers = customerService.findAllByProvince(provinceName);
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Customer> getCustomer(@PathVariable("id") Long id) throws NotFoundException {
        Customer customer = customerService.findById(id);
        return new ResponseEntity<>(customer, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Void> createCustomer(@RequestBody Customer customer, UriComponentsBuilder builder) throws DuplicateException {
        customerService.save(customer);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(builder.path("/customers/{id}").buildAndExpand(customer.getId()).toUri());
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable("id") Long id, @RequestBody Customer customer) throws NotFoundException, DuplicateException {
        Customer currentCustomer = customerService.findById(id);
        currentCustomer.setName(customer.getName());
        currentCustomer.setAddress(customer.getAddress());
        currentCustomer.setEmail(customer.getEmail());
        currentCustomer.setProvince(customer.getProvince());
        customerService.save(currentCustomer);
        return new ResponseEntity<>(currentCustomer, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Customer> deleteCustomer(@PathVariable("id") Long id) throws NotFoundException {
        Customer customer = customerService.findById(id);
        customerService.delete(customer);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public void handleNotFound() {
    }

    @ExceptionHandler(DuplicateException.class)
    public ResponseEntity<Void> handleDuplicate() {
        return new ResponseEntity<>(HttpStatus.CONFLICT);
    }
}
