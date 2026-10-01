package za.ac.cput.controller;

import org.springframework.web.bind.annotation.*;
import za.ac.cput.domain.Customer;
import za.ac.cput.service.CustomerService;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    private final CustomerService service;

    public CustomerController(CustomerService service){
        this.service = service;
    }

    @PostMapping("/create")
    public Customer create(@RequestBody Customer customer){
        return service.create(customer);
    }

    @GetMapping("/read/{id}")
    public Customer read(@PathVariable int id){
        return service.read(id);
    }

    @PutMapping("/update")
    public Customer update(@RequestBody Customer customer){
        return service.update(customer);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable int id){
        service.delete(id);
    }

    @GetMapping("/email/{email}")
    public Customer findByEmail(@PathVariable String email){
        return service.findByEmail(email);
    }
}
