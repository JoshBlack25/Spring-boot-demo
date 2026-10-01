package za.ac.cput.factory;

import org.junit.jupiter.api.Test;
import za.ac.cput.domain.Customer;
import za.ac.cput.domain.Electronic;
import za.ac.cput.domain.valueObject.Name;

import static org.junit.jupiter.api.Assertions.*;

public class ElectronicFactoryTest {

    private Customer validCustomer(){
        Name name = NameFactory.buildName(
                "Joshua",
                "OG",
                "Adams"
        );
        return CustomerFactory.buildCustomer(
                name, "Joshua.Adams@customer.com", "0671234567"
        );
    }

    @Test
    void testBuildElectronic(){

        Electronic electronic = ElectronicFactory.buildElectronic(
                "Samsang",
                2000.0,
                validCustomer(),
                200
        );

        assertNotNull(electronic);
        assertEquals("Samsang", electronic.getBrand());
        assertEquals(2000.0, electronic.getPrice());
        assertEquals("Joshua", electronic.getCustomer().getName().getFirstName());
        assertEquals(200, electronic.getVoltage());
    }

    @Test
    void testBuildElectronicIsNull(){

        Electronic electronic = ElectronicFactory.buildElectronic(
                null,
                2000.0,
                validCustomer(),
                200
        );

        assertNull(electronic);
    }

    @Test
    void testBuildElectronicIsEmpty(){
        Electronic electronic = ElectronicFactory.buildElectronic(
                "",
                2000.0,
                validCustomer(),
                200
        );

        assertNull(electronic);
    }

}
