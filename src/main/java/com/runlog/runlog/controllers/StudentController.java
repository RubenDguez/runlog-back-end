package com.runlog.runlog.controllers;

import com.runlog.runlog.entity.Student;
import com.runlog.runlog.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RequestMapping(value = "/students")
@RestController
public class StudentController {

    @Autowired
    StudentRepository studentRepository;

    @PostMapping
    public Student postStudent(@Valid @RequestBody Student student) {
        return studentRepository.save(student);
    }

}
