package com.student.student.service;

import com.student.student.constant.StatusStudent;
import com.student.student.entity.Student;
import com.student.student.model.StudentDto;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.ObjectUtils;

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
                .status(ObjectUtils.isEmpty(student.getStatus()) ? StatusStudent.ACTIVO.name() : student.getStatus())
                .build();
    }
}
