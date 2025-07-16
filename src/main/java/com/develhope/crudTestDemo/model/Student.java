package com.develhope.crudTestDemo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
@AllArgsConstructor
@Setter
@Getter
@Entity
@Table
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length= 100, nullable = false)
    private String name;
    @Column(length= 100,nullable = false)
    private String surname;
    @Column(nullable = false)
    private boolean isWorking;

    public Student() {

    }
}
