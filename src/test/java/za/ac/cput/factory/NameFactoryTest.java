package za.ac.cput.factory;

import org.junit.jupiter.api.Test;
import za.ac.cput.domain.valueObject.Name;

import static org.junit.jupiter.api.Assertions.*;

public class NameFactoryTest {

    @Test
    void testBuildName(){
        Name name = NameFactory.buildName(
                "Joshua",
                "OG",
                "Adams"
        );

        assertNotNull(name);
        assertEquals("Joshua", name.getFirstName());
        assertEquals("OG", name.getMiddleName());
        assertEquals("Adams", name.getLastName());
    }

    @Test
    void testBuildNameIsNull(){
        Name name = NameFactory.buildName(
                "Joshua",
                null,
                "Adams"
        );

        assertNotNull(name);
        assertEquals("Joshua", name.getFirstName());
        assertNull(name.getMiddleName());
        assertEquals("Adams", name.getLastName());
    }

    @Test
    void testBuildNameIsEmpty(){
        Name name = NameFactory.buildName(
                "",
                "OG",
                "Adams"
        );

        assertNull(name);
    }
}
