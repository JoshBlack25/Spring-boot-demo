package za.ac.cput.service;

import za.ac.cput.domain.Student;

import java.util.List;

public interface IStudentService {
    Student create(Student student);
    Student read(String id);
    Student update(Student student);
    void delete(String id);
    List<Student> getAll();
}
