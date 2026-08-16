package za.ac.cput.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import za.ac.cput.domain.Person;
import za.ac.cput.domain.Student;
import za.ac.cput.repository.StudentRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class StudentServiceTest {

    @Mock
    private StudentRepository repository;

    @InjectMocks
    private StudentService service;

    private Student student;
    private Person person;

    @BeforeEach
    void setUp() {
        person = new Person.Builder()
                .setFirstName("Joshua")
                .setLastName("Adams")
                .build();

        student = new Student.Builder()
                .setStudentId("230317693")
                .setPerson(person)
                .setCredits(120.00)
                .build();
    }

    @Test
    void testCreate() {
        Mockito.when(repository.save(student)).thenReturn(student);

        Student result = service.create(student);

        assertNotNull(result);
        assertEquals("230317693", result.getStudentId());
    }

    @Test
    void testRead() {
        Mockito.when(repository.findById("230317693")).thenReturn(Optional.of(student));

        Student result = service.read("230317693");

        assertNotNull(result);
        assertEquals("Joshua", result.getPerson().getFirstName());
    }

    @Test
    void testUpdate() {
        // Create an updated student instance with altered credits
        Student updatedStudent = new Student.Builder()
                .setStudentId("230317693")
                .setPerson(person)
                .setCredits(150.00)
                .build();

        Mockito.when(repository.save(updatedStudent)).thenReturn(updatedStudent);

        Student result = service.update(updatedStudent);

        assertNotNull(result);
        assertEquals(150.00, result.getCredits());
    }

    @Test
    void testDelete() {
        String studentId = "230317693";

        // deleteById returns void, so we instruct Mockito to do nothing
        Mockito.doNothing().when(repository).deleteById(studentId);

        service.delete(studentId);

        // Verifies that the repository method was actually executed exactly once
        Mockito.verify(repository, Mockito.times(1)).deleteById(studentId);
    }

    @Test
    void testGetAll() {
        List<Student> studentList = Arrays.asList(student);
        Mockito.when(repository.findAll()).thenReturn(studentList);

        List<Student> result = service.getAll();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertFalse(result.isEmpty());
    }
}
