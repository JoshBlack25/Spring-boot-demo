package za.ac.cput.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import za.ac.cput.domain.Customer;
import za.ac.cput.domain.Electronic;
import za.ac.cput.domain.Product;
import za.ac.cput.domain.valueObject.Name;
import za.ac.cput.service.ElectronicService;
import za.ac.cput.service.ProductService;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ProductControllerTest {

    @Mock
    private ProductService productService;

    @Mock
    private ElectronicService electronicService;

    @InjectMocks
    private ProductController controller;

    private Customer customer1;
    private Electronic electronic1;

    @BeforeEach
    void setUp() {
        Name name = new Name.Builder()
                .setFirstName("Thabo")
                .setLastName("Nkosi")
                .build();

        customer1 = new Customer.Builder()
                .setCustomerId(101)
                .setName(name)
                .setEmail("thabo@customer.com")
                .setMobile("0712345678")
                .build();

        electronic1 = new Electronic.Builder()
                .setProductId(1)
                .setBrand("Samsung")
                .setPrice(12000.00)
                .setCustomer(customer1)
                .setVoltage(220)
                .build();
    }

    // ---------- create: Electronic ----------

    @Test
    void testCreateElectronic() {
        when(electronicService.create(electronic1)).thenReturn(electronic1);

        Electronic result = controller.createElectronic(electronic1);

        assertNotNull(result);
        assertEquals("Samsung", result.getBrand());
        assertEquals(220, result.getVoltage());
        verify(electronicService).create(electronic1);
    }

    @Test
    void testCreateElectronic_invalidData_returnsNull() {
        Electronic invalidElectronic = new Electronic.Builder().copy(electronic1).setPrice(0.0).build();
        when(electronicService.create(invalidElectronic)).thenReturn(null);

        Electronic result = controller.createElectronic(invalidElectronic);

        assertNull(result);
        verify(electronicService).create(invalidElectronic);
    }

    // ---------- read ----------

    @Test
    void testRead() {
        when(productService.read(1)).thenReturn(electronic1);

        Product result = controller.read(1);

        assertNotNull(result);
        assertEquals(1, result.getProductId());
        verify(productService).read(1);
    }

    @Test
    void testReadNotFound() {
        when(productService.read(404)).thenReturn(null);

        Product result = controller.read(404);

        assertNull(result);
        verify(productService).read(404);
    }

    // ---------- update: Electronic ----------

    @Test
    void testUpdateElectronic() {
        when(electronicService.update(electronic1)).thenReturn(electronic1);

        Electronic result = controller.updateElectronic(electronic1);

        assertNotNull(result);
        assertEquals(1, result.getProductId());
        verify(electronicService).update(electronic1);
    }

    // ---------- delete ----------

    @Test
    void testDelete() {
        controller.update(1);

        verify(productService).delete(1);
    }

    // ---------- getAll ----------

    @Test
    void testGetAll() {
        List<Product> products = Arrays.asList(electronic1);
        when(productService.getAll()).thenReturn(products);

        List<Product> result = controller.getAll();

        assertNotNull(result);
        assertEquals(1, result.size());
        verify(productService).getAll();
    }

    // ---------- getByCustomerId ----------

    @Test
    void testGetByCustomerId() {
        when(productService.findByCustomerId(101)).thenReturn(Arrays.asList(electronic1));

        List<Product> result = controller.getByCustomerId(101);

        assertNotNull(result);
        assertEquals(1, result.size());
        verify(productService).findByCustomerId(101);
    }
}