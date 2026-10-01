package za.ac.cput.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import za.ac.cput.domain.Customer;
import za.ac.cput.domain.valueObject.Name;
import za.ac.cput.repository.CustomerRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class CustomerServiceTest {

    @Mock
    private CustomerRepository customerRepository;


    @InjectMocks
    private CustomerService customerService;

    private Customer customer1;
    private Customer customer2;

    @BeforeEach
    void SetUp(){
        MockitoAnnotations.openMocks(this);

        Name name1 = new Name.Builder()
                .setFirstName("John")
                .setLastName("Doe")
                .build();

        Name name2 = new Name.Builder()
                .setFirstName("Jane")
                .setLastName("Doe")
                .build();

        customer1 = new Customer.Builder()
                .setCustomerId(1)
                .setName(name1)
                .setEmail("John.Doe@customer.com")
                .setMobile("0671234567")
                .build();

        customer2 = new Customer.Builder()
                .setCustomerId(2)
                .setName(name2)
                .setEmail("Jane.Doe@customer.com")
                .setMobile("0671234567")
                .build();
    }

    //  ----- create -----
    @Test
    void testCreate(){
        when(customerRepository.save(any(Customer.class))).thenReturn(customer1);

        Customer result = customerService.create(customer1);

        assertNotNull(result);
        assertEquals(customer1.getEmail(), result.getEmail());
        verify(customerRepository, times(1)).save(any(Customer.class));
    }

    //  ----- read -----
    @Test
    void testRead(){
        when(customerRepository.findById(1)).thenReturn(Optional.of(customer1));

        Customer result = customerService.read(99);

        assertNull(result);
    }

    //  ----- update -----
    @Test
    void testUpdate(){
        when(customerRepository.existsById(customer1.getCustomerId())).thenReturn(true);
        when(customerRepository.save(any(Customer.class))).thenAnswer(inv -> inv.getArgument(0));

        Customer result = customerService.update(customer1);

        ArgumentCaptor<Customer> captor = ArgumentCaptor.forClass(Customer.class);
        verify(customerRepository, times(1)).existsById(customer1.getCustomerId());
        verify(customerRepository, times(1)).save(captor.capture());

        assertNotNull(result);
        assertEquals(customer1.getCustomerId(), result.getCustomerId());
        assertEquals(customer1.getCustomerId(), captor.getValue().getCustomerId());
    }

    //  ----- delete -----
    @Test
    void testDelete(){
        when(customerRepository.existsById(1)).thenReturn(true);

        customerService.delete(1);

        verify(customerRepository, times(1)).deleteById(1);
    }

    // ----- getAll -----
    @Test
    void testGetAll(){
        when(customerRepository.findAll()).thenReturn(Arrays.asList(customer1, customer2));

        List<Customer> customers = customerService.getAll();

        assertEquals(2, customers.size());
        verify(customerRepository, times(1)).findAll();
    }

    //  ----- findByEmail -----
    @Test
    void testFindByEmail(){
        when(customerRepository.findByEmail("John.Doe@customer.com")).thenReturn(customer1);

        Customer result = customerService.findByEmail("John.Doe@customer.com");

        assertNotNull(result);
        assertEquals(customer1.getCustomerId(), result.getCustomerId());
    }
}
