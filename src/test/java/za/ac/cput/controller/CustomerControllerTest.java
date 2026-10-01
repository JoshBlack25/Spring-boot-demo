package za.ac.cput.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import za.ac.cput.domain.Customer;
import za.ac.cput.domain.valueObject.Name;
import za.ac.cput.service.CustomerService;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CustomerControllerTest {

    @Mock
    private CustomerService service;

    @InjectMocks
    private CustomerController controller;

    private Customer createTestCustomer() {
        Name name = new Name.Builder()
                .setFirstName("Joshua")
                .setLastName("Adams")
                .build();

        return new Customer.Builder()
                .setCustomerId(101)
                .setName(name)
                .setEmail("joshua.adams@customer.com")
                .setMobile("0671234567")
                .build();
    }

    @Test
    void testCreate() {
        Customer customer = createTestCustomer();
        when(service.create(customer)).thenReturn(customer);

        Customer result = controller.create(customer);

        assertNotNull(result);
        assertEquals(101, result.getCustomerId());
        assertEquals("Joshua", result.getName().getFirstName());
        verify(service).create(customer);
    }

    @Test
    void testRead() {
        Customer customer = createTestCustomer();
        when(service.read(101)).thenReturn(customer);

        Customer result = controller.read(101);

        assertNotNull(result);
        assertEquals(101, result.getCustomerId());
        verify(service).read(101);
    }

    @Test
    void testReadNotFound() {
        when(service.read(999)).thenReturn(null);

        Customer result = controller.read(999);

        assertNull(result);
        verify(service).read(999);
    }

    @Test
    void testUpdate() {
        Customer customer = createTestCustomer();
        when(service.update(customer)).thenReturn(customer);

        Customer result = controller.update(customer);

        assertNotNull(result);
        assertEquals(101, result.getCustomerId());
        verify(service).update(customer);
    }

    @Test
    void testDelete() {
        controller.delete(101);

        verify(service).delete(101);
    }

    @Test
    void testFindByEmail() {
        Customer customer = createTestCustomer();
        when(service.findByEmail("joshua.adams@customer.com")).thenReturn(customer);

        Customer result = controller.findByEmail("joshua.adams@customer.com");

        assertNotNull(result);
        assertEquals("joshua.adams@customer.com", result.getEmail());
        verify(service).findByEmail("joshua.adams@customer.com");
    }
}