package com.runlog.runlog.repository;

import com.runlog.runlog.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    Student findByEmailAddress(String emailAddress);
}
