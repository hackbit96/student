package com.student.student.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Table(name = "student")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Student {

    @Id
    @Column(value = "id")
    private Long id;

    @Column(value = "name")
    private String name;

    @Column(value = "last_name")
    private String lastName;

    @Column(value = "age")
    private Integer age;

    @Column(value = "status")
    private String status;

}
