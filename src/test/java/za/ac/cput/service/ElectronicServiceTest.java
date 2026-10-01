package za.ac.cput.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import za.ac.cput.domain.Customer;
import za.ac.cput.domain.Electronic;
import za.ac.cput.domain.valueObject.Name;
import za.ac.cput.repository.ElectronicRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ElectronicServiceTest {

    @Mock
    private ElectronicRepository electronicRepository;

    @InjectMocks
    private ElectronicService electronicService;

    private Electronic electronic1;
    private Electronic electronic2;

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

        electronic1 = new Electronic.Builder()
                .setProductId(1)
                .setBrand("Samsung")
                .setPrice(2500.0)
                .setCustomer(customer)
                .setVoltage(220)
                .build();

        electronic2 = new Electronic.Builder()
                .setProductId(2)
                .setBrand("LG")
                .setPrice(3500.0)
                .setCustomer(customer)
                .setVoltage(240)
                .build();
    }

    // ----- create -----
    @Test
    void testCreate() {
        when(electronicRepository.save(any(Electronic.class))).thenReturn(electronic1);

        Electronic result = electronicService.create(electronic1);

        assertNotNull(result);
        assertEquals(electronic1.getBrand(), result.getBrand());
        assertEquals(electronic1.getPrice(), result.getPrice());
        verify(electronicRepository, times(1)).save(any(Electronic.class));
    }

    // ----- read -----
    @Test
    void testRead() {
        when(electronicRepository.findById(1)).thenReturn(Optional.of(electronic1));

        Electronic found = electronicService.read(1);
        Electronic notFound = electronicService.read(99);

        assertNotNull(found);
        assertEquals(electronic1.getProductId(), found.getProductId());
        assertNull(notFound);
        verify(electronicRepository, times(1)).findById(1);
        verify(electronicRepository, times(1)).findById(99);
    }

    // ----- update -----
    @Test
    void testUpdate() {
        when(electronicRepository.existsById(electronic1.getProductId())).thenReturn(true);
        when(electronicRepository.save(any(Electronic.class))).thenAnswer(inv -> inv.getArgument(0));

        Electronic result = electronicService.update(electronic1);

        ArgumentCaptor<Electronic> captor = ArgumentCaptor.forClass(Electronic.class);
        verify(electronicRepository, times(1)).existsById(electronic1.getProductId());
        verify(electronicRepository, times(1)).save(captor.capture());

        assertNotNull(result);
        assertEquals(electronic1.getProductId(), result.getProductId());
        assertEquals(electronic1.getProductId(), captor.getValue().getProductId());
    }

    // ----- delete -----
    @Test
    void testDelete() {
        when(electronicRepository.existsById(1)).thenReturn(true);

        electronicService.delete(1);

        verify(electronicRepository, times(1)).existsById(1);
        verify(electronicRepository, times(1)).deleteById(1);
    }

    // ----- getAll -----
    @Test
    void testGetAll() {
        when(electronicRepository.findAll()).thenReturn(Arrays.asList(electronic1, electronic2));

        List<Electronic> electronics = electronicService.getAll();

        assertNotNull(electronics);
        assertEquals(2, electronics.size());
        verify(electronicRepository, times(1)).findAll();
    }

    // ----- findByCustomerId -----
    @Test
    void testFindByCustomerId() {
        when(electronicRepository.findByCustomerId(1)).thenReturn(Arrays.asList(electronic1, electronic2));

        List<Electronic> result = electronicService.findByCustomerId(1);

        assertNotNull(result);
        assertEquals(2, result.size());
        verify(electronicRepository, times(1)).findByCustomerId(1);
    }
}