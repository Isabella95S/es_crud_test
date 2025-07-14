package com.develhope.crudTestDemo.controller;

import com.develhope.crudTestDemo.model.Student;
import com.develhope.crudTestDemo.repository.StudentRepository;
import com.develhope.crudTestDemo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/student")
public class StudentController {
    @Autowired
    private StudentRepository studentRepo;
    @Autowired
    private StudentService studentService;
    @PostMapping("/")
    public void create(@RequestBody Student student) {
    studentRepo.save(student);
    }
    @GetMapping("/{id}")
    public Student getStudent(@PathVariable Integer id) {
        Optional<Student> student = studentRepo.findById(id);
        if (student.isEmpty()) {
            return student.get();
        }else {
            return null;
        }
    }
    @GetMapping("/students")
    public List<Student> getList() {
    return studentRepo.findAll();
    }
    @PutMapping("/")
    public void update() {

    }
    @DeleteMapping()
    public void delete() {

    }
}
