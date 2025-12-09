package com.example.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Entity
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    private String firstName;
    @Setter
    private String lastName;
    @Setter
    private String rollNumber;
    @Setter
    private String className;

    public Student() {
    }

    public Student(String firstName, String lastName, String rollNumber, String className) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.rollNumber = rollNumber;
        this.className = className;
    }

    // Getters and setters (right-click → Generate → Getter and Setter)

}
