package com.student.student.mock;

import com.student.student.constant.StatusStudent;
import com.student.student.entity.Student;
import com.student.student.model.StudentDto;

public class MockData {

    public static StudentDto mapperStudent() {
        return StudentDto.builder()
                .id(2L)
                .name("Orlando")
                .lastName("Camavilca Chavez")
                .age(20)
                .status(StatusStudent.ACTIVO.name())
                .build();
    }

    public static StudentDto mapperStudentBadRequest() {
        return StudentDto.builder()
                .id(null)
                .name("Orlando")
                .lastName("Camavilca Chavez")
                .age(20)
                .status(StatusStudent.ACTIVO.name())
                .build();
    }

    public static Student mapperStudentActivo() {
        return Student.builder()
                .id(2L)
                .name("Orlando")
                .lastName("Camavilca Chavez")
                .age(20)
                .status(StatusStudent.ACTIVO.name())
                .build();
    }

    public static Student mapperStudentInactivo() {
        return Student.builder()
                .id(1L)
                .name("Orlando")
                .lastName("Camavilca Chavez")
                .age(20)
                .status(StatusStudent.INACTIVO.name())
                .build();
    }

}
