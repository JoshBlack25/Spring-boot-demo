package za.ac.cput.factory;

import org.junit.jupiter.api.Test;
import za.ac.cput.domain.Person;
import za.ac.cput.domain.Student;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class StudentFactoryTest {
    @Test
    void testCreateStudent(){
        Person person = new Person.Builder()
                .setFirstName("Joshua")
                .setLastName("Adams")
                .build();

        Student student = StudentFactory.createStudent("12345", person, 120.00);

        assertNotNull(student);
    }
}
