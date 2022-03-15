package com.runlog.runlog.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.runlog.runlog.DTO.StudentDTO;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "tbl_student", uniqueConstraints = @UniqueConstraint(name = "emailAddress_unique", columnNames = "email_address"))
public class Student {

        @Id
        @SequenceGenerator(name = "student_sequence", sequenceName = "student_sequence", allocationSize = 1)
        @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "student_sequence")
        private Long id;

        @NotNull(message = "First Name is required")
        @NotEmpty(message = "First Name is required")
        @NotBlank(message = "First Name is required")
        @Column(name = "first_name", nullable = false)
        private String firstName;

        @Column(name = "last_name")
        private String lastName;

        @Column(name = "email_address")
        private String emailAddress;

        public StudentDTO toDTO() {
                return StudentDTO.builder()
                                .id(this.getId())
                                .firstName(this.getFirstName())
                                .lastName(this.getLastName())
                                .emailAddress(this.getEmailAddress())
                                .build();
        }

        public static StudentDTO toDTO(Student student) {
                return StudentDTO
                                .builder()
                                .id(student.getId())
                                .firstName(student.getFirstName())
                                .lastName(student.getLastName())
                                .emailAddress(student.getEmailAddress())
                                .build();
        }

}
