package za.ac.cput.factory;

import org.junit.jupiter.api.Test;
import za.ac.cput.domain.Customer;
import za.ac.cput.domain.valueObject.Name;

import static org.junit.jupiter.api.Assertions.*;

public class CustomerFactoryTest {

    private Name validName(){
        return NameFactory.buildName(
            "Joshua",
                "OG",
                "Adams"
        );
    }

    @Test
    void testBuildCustomer(){

        Customer customer = CustomerFactory.buildCustomer(
                validName(),
                "joshua.adams@customer.com",
                "0671234567"
        );

        assertNotNull(customer);
        assertEquals("Joshua", customer.getName().getFirstName());
        assertEquals("joshua.adams@customer.com", customer.getEmail());
        assertEquals("0671234567", customer.getMobile());

    }

    @Test
    void testBuildCustomerIsNull(){

        Customer customer = CustomerFactory.buildCustomer(
                null,
                "joshua.adams@customer.com",
                "0671234567"
        );

        assertNull(customer);
    }

    @Test
    void testBuildCustomerIsEmpty(){
        Customer customer = CustomerFactory.buildCustomer(
                validName(),
                "",
                "0671234567"
        );

        assertNull(customer);
    }
}
