package com.develhope.crudTestDemo.service;

import com.develhope.crudTestDemo.model.Student;
import com.develhope.crudTestDemo.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public void setWorkingStatus(Integer id, boolean isWorking) {
        Optional<Student> stud = studentRepository.findById(id);
        if (stud.isPresent()) return;
        stud.get().setWorking(isWorking);
        studentRepository.save(stud.get());
    }
}
