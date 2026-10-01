package za.ac.cput.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import za.ac.cput.domain.Customer;
import za.ac.cput.domain.Electronic;
import za.ac.cput.domain.Product;
import za.ac.cput.domain.valueObject.Name;
import za.ac.cput.repository.ProductRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;

    private Product product1;
    private Product product2;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        Name name = new Name.Builder()
                .setFirstName("John")
                .setLastName("Doe")
                .build();

        Customer customer = new Customer.Builder()
                .setCustomerId(1)
                .setName(name)
                .setEmail("John.Doe@customer.com")
                .setMobile("0671234567")
                .build();

        product1 = new Electronic.Builder()
                .setProductId(1)
                .setBrand("Sony")
                .setPrice(1500.0)
                .setCustomer(customer)
                .setVoltage(220)
                .build();

        product2 = new Electronic.Builder()
                .setProductId(2)
                .setBrand("Panasonic")
                .setPrice(1800.0)
                .setCustomer(customer)
                .setVoltage(110)
                .build();
    }

    // ----- create -----
    @Test
    void testCreate() {
        Product result = productService.create(product1);
        assertNull(result); // Matches ProductService returning null
    }

    // ----- read -----
    @Test
    void testRead() {
        when(productRepository.findById(1)).thenReturn(Optional.of(product1));

        Product found = productService.read(1);
        Product notFound = productService.read(99);

        assertNotNull(found);
        assertEquals(product1.getProductId(), found.getProductId());
        assertNull(notFound);
        verify(productRepository, times(1)).findById(1);
        verify(productRepository, times(1)).findById(99);
    }

    // ----- update -----
    @Test
    void testUpdate() {
        Product result = productService.update(product1);
        assertNull(result); // Matches ProductService returning null
    }

    // ----- delete -----
    @Test
    void testDelete() {
        when(productRepository.existsById(1)).thenReturn(true);

        productService.delete(1);

        verify(productRepository, times(1)).existsById(1);
        verify(productRepository, times(1)).deleteById(1);
    }

    // ----- getAll -----
    @Test
    void testGetAll() {
        when(productRepository.findAll()).thenReturn(Arrays.asList(product1, product2));

        List<Product> products = productService.getAll();

        assertNotNull(products);
        assertEquals(2, products.size());
        verify(productRepository, times(1)).findAll();
    }

    // ----- findByCustomerId -----
    @Test
    void testFindByCustomerId() {
        when(productRepository.findByCustomerId(1)).thenReturn(Arrays.asList(product1, product2));

        List<Product> result = productService.findByCustomerId(1);

        assertNotNull(result);
        assertEquals(2, result.size());
        verify(productRepository, times(1)).findByCustomerId(1);
    }
}