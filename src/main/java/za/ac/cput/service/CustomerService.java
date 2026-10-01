package za.ac.cput.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.cput.domain.Customer;
import za.ac.cput.factory.CustomerFactory;
import za.ac.cput.repository.CustomerRepository;
import za.ac.cput.service.impl.ICustomerService;

import java.util.List;

@Service
public class CustomerService implements ICustomerService {

    private final CustomerRepository repository;

    @Autowired
    public CustomerService(CustomerRepository repository) {
        this.repository = repository;
    }

    @Override
    public Customer findByEmail(String email) {
        return repository.findByEmail(email);
    }

    @Override
    public Customer create(Customer customer) {
        if (customer == null) return null;

        Customer validated = CustomerFactory.buildCustomer(
                customer.getName(),
                customer.getEmail(),
                customer.getMobile()
        );

        if (validated == null) return null;

        return repository.save(validated);
    }

    @Override
    public Customer read(Integer id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public Customer update(Customer customer) {
        if (customer == null) return null;
        if (!repository.existsById(customer.getCustomerId())) return null;

        Customer validated = CustomerFactory.buildCustomer(
                customer.getName(),
                customer.getEmail(),
                customer.getMobile()
        );

        if (validated == null) return null;

        Customer toSave = new Customer.Builder()
                .copy(validated)
                .setCustomerId(customer.getCustomerId())
                .build();

        return repository.save(toSave);
    }

    @Override
    public void delete(Integer id) {
        if (repository.existsById(id)){
            repository.deleteById(id);
        }

    }

    @Override
    public List<Customer> getAll() {
        return repository.findAll();
    }
}
