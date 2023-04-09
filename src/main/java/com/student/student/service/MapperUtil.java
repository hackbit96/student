package com.student.student.service;

import com.student.student.constant.StatusStudent;
import com.student.student.entity.Student;
import com.student.student.model.StudentDto;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MapperUtil {

    public static StudentDto mapperStudent(Student student) {
        return StudentDto.builder()
                .id(student.getId())
                .name(student.getName())
                .lastName(student.getLastName())
                .age(student.getAge())
                .status(student.getStatus())
                .build();
    }

    public static Student mapperStudentCreate(StudentDto student) {
        return Student.builder()
                .id(student.getId())
                .name(student.getName())
                .lastName(student.getLastName())
                .age(student.getAge())
                .status(StatusStudent.ACTIVO.name())
                .build();
    }
}
