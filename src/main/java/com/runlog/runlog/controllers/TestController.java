package com.runlog.runlog.controllers;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import com.runlog.runlog.DTO.StudentDTO;
import com.runlog.runlog.entity.Student;
import com.runlog.runlog.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(value = "/api/students")
@RestController
public class TestController {

    @Autowired
    StudentRepository studentRepository;

    @GetMapping()
    public List<StudentDTO> getAllStudents() {
        List<StudentDTO> studentsList = new LinkedList<>();
        studentRepository.findAll().forEach(student -> {
            studentsList.add(
                    StudentDTO.builder()
                            .id(student.getId())
                            .firstName(student.getFirstName())
                            .lastName(student.getLastName())
                            .emailAddress(student.getEmailAddress())
                            .build());
        });

        return studentsList;
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<StudentDTO> getStudentById(@PathVariable Long id) {
        Optional<Student> student = studentRepository.findById(id);

        if (student.isEmpty())
            return new ResponseEntity<StudentDTO>(HttpStatus.NOT_FOUND);

        StudentDTO stDTO = Student.toDTO(student.get());

        return new ResponseEntity<StudentDTO>(stDTO, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<StudentDTO> postStudent(@Valid @RequestBody StudentDTO studentDTO) {

        if (studentRepository.findByEmailAddress(studentDTO.getEmailAddress()) != null)
            return new ResponseEntity<StudentDTO>(studentDTO, HttpStatus.BAD_REQUEST);

        Student student = Student.builder()
                .firstName(studentDTO.getFirstName())
                .lastName(studentDTO.getLastName())
                .emailAddress(studentDTO.getEmailAddress())
                .build();

        System.out.println(studentDTO);
        System.out.println(student);

        StudentDTO response = studentRepository.save(student).toDTO();
        return new ResponseEntity<StudentDTO>(response, HttpStatus.CREATED);

    }

}
