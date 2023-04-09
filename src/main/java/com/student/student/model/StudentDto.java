package com.student.student.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StudentDto {

    @NotNull(message = "Id cannot be null or empty")
    private Long id;

    @NotNull(message = "Name cannot be null or empty")
    @NotEmpty(message = "Name cannot be null or empty")
    private String name;

    @NotNull(message = "Last Name cannot be null or empty")
    @NotEmpty(message = "Last Name cannot be null or empty")
    private String lastName;

    @NotNull(message = "Age cannot be null or empty")
    private Integer age;

    private String status;

}
