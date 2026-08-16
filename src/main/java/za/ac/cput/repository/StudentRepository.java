package za.ac.cput.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import za.ac.cput.domain.Student;

public interface StudentRepository extends JpaRepository<Student, String> {
}
