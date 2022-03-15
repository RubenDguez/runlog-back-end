package com.runlog.runlog.DTO;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class StudentDTO {

    private Long id;

    @NotNull(message = "First name is required")
    @NotEmpty(message = "First name is required")
    private String firstName;

    private String lastName;

    private String emailAddress;

    public boolean isValid() {
        return this.firstName != null && this.lastName != null && this.emailAddress != null;
    }
}
