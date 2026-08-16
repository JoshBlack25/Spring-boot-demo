package za.ac.cput.controller;

import org.springframework.web.bind.annotation.*;
import za.ac.cput.domain.Student;
import za.ac.cput.service.StudentService;

import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    private final StudentService service;

    public StudentController(StudentService service){
        this.service = service;
    }

    @PostMapping
    public Student create(@RequestBody Student student){
        return service.create(student);
    }

    @GetMapping("/{id}")
    public Student read(@PathVariable String id){
        return service.read(id);
    }

    @PutMapping
    public Student update(@RequestBody Student student){
        return service.update(student);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id){
        service.delete(id);
    }

    @GetMapping
    public List<Student> getAll(){
        return service.getAll();
    }

}
