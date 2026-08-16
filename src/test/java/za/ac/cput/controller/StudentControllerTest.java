package za.ac.cput.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import za.ac.cput.domain.Person;
import za.ac.cput.domain.Student;
import za.ac.cput.service.StudentService;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class StudentControllerTest {

    @Mock
    private StudentService service;

    @InjectMocks
    private StudentController controller;

    private Student createTestStudent(){
        Person person = new Person.Builder()
                .setFirstName("Joshua")
                .setLastName("Adams")
                .build();

        return new Student.Builder()
                .setStudentId("230317693")
                .setPerson(person)
                .setCredits(99.99)
                .build();
    }

    @Test
    void testCreate(){
        Student student = createTestStudent();
        when(service.create(student)).thenReturn(student);

        Student result = controller.create(student);

        // FIXED: Now accurately verifying the actual object's ID field
        assertNotNull(result.getStudentId());
        assertEquals("Joshua", result.getPerson().getFirstName());
        verify(service).create(student);
    }

    @Test
    void testRead(){
        Student student = createTestStudent();
        when(service.read("230317693")).thenReturn(student);

        Student result = controller.read("230317693");
        assertNotNull(result);
        assertEquals("230317693", result.getStudentId());
        verify(service).read("230317693");
    }

    @Test
    void testReadNotfound(){
        when(service.read("99999")).thenReturn(null);

        Student result = controller.read("99999");
        assertNull(result);
        verify(service).read("99999");
    }

    @Test
    void testGetAll(){
        when(service.getAll()).thenReturn(List.of());
        List<?> result = controller.getAll();
        assertNotNull(result);
        verify(service).getAll();
    }
}
