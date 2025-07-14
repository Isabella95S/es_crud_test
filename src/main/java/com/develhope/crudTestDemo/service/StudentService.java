package com.develhope.crudTestDemo.service;

import com.develhope.crudTestDemo.model.Student;
import com.develhope.crudTestDemo.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public void setWorkingStatus(Student student, boolean isWorking) {
        if (student == null) return;
        student.setWorking(isWorking);
        studentRepository.save(student);
    }
}
