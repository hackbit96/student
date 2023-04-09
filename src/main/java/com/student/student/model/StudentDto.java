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

    @NotNull(message = "Id no puede ser nulo o vacío")
    private Long id;

    @NotNull(message = "Name no puede ser nulo o vacío")
    @NotEmpty(message = "Name no puede ser nulo o vacío")
    private String name;

    @NotNull(message = "Last Name no puede ser nulo o vacío")
    @NotEmpty(message = "Last Name no puede ser nulo o vacío")
    private String lastName;

    @NotNull(message = "Age no puede ser nulo o vacío")
    private Integer age;

    @Pattern(regexp = "^(ACTIVO|INACTIVO)$", message = "Solo es permitido el valor ACTIVO ó INACTIVO")
    private String status;

}
