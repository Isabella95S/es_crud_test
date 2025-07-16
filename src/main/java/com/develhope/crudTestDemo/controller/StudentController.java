package com.develhope.crudTestDemo.controller;

import com.develhope.crudTestDemo.model.Student;
import com.develhope.crudTestDemo.repository.StudentRepository;
import com.develhope.crudTestDemo.service.StudentService;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/student")
public class StudentController {
    @Autowired
    private StudentRepository studentRepo;
    @Autowired
    private StudentService studentService;
    @PostMapping("")
    public Student create(@RequestBody Student student) {
    return studentRepo.save(student);
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
    @PutMapping("/{id}")
    public void update(@PathVariable Integer id , @NotNull @RequestBody Student stud) {
         stud.setId(id);
         studentRepo.save(stud);
    }
    @PutMapping("/{id}/working")
    public void setStudentWorking(@PathVariable Integer id, @RequestParam boolean working) {
    studentService.setWorkingStatus(id,working);
    }
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        studentRepo.deleteById(id);
    }
}
