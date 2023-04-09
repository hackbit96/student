package com.student.student.service.impl;

import com.student.student.constant.StatusStudent;
import com.student.student.entity.Student;
import com.student.student.exception.StudentAlreadyExistsException;
import com.student.student.mock.MockData;
import com.student.student.model.StudentDto;
import com.student.student.repository.StudentRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.util.ReflectionTestUtils;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
public class StudentServiceImplTest {

    @Mock
    private StudentRepository repository;

    @InjectMocks
    private StudentServiceImpl studentService;

    private final String errorMessage = "Student with id %s already exists";
    private final String errorCode = "STUDENT_ALREADY_EXISTS";
    private String emptyMessage = "No active students found";
    private String emptyCode = "NO_ACTIVE_STUDENTS";

    @Test
    public void testCreateStudent() {
        ReflectionTestUtils.setField(studentService, "errorMessage", errorMessage);
        ReflectionTestUtils.setField(studentService, "errorCode", errorCode);

        StudentDto studentDto = MockData.mapperStudent();
        Student student = MockData.mapperStudentActivo();
        when(repository.findById(studentDto.getId()))
                .thenReturn(Mono.empty());
        when(repository.create(any(Student.class)))
                .thenReturn(Mono.empty());
        when(repository.findById(studentDto.getId()))
                .thenReturn(Mono.just(student));

        StepVerifier.create(studentService.create(studentDto))
                .expectNextMatches(result -> {
                    assertEquals(1L, result.getId());
                    assertEquals(StatusStudent.ACTIVO.name(), result.getStatus());
                    return true;
                })
                .verifyComplete();

        verify(repository, times(1)).findById(studentDto.getId());
        verify(repository, times(1)).create(any(Student.class));
        verify(repository, times(2)).findById(studentDto.getId());
    }

    @Test
    public void create_WithExistingStudent_ThrowsException() {
        ReflectionTestUtils.setField(studentService, "errorMessage", errorMessage);
        ReflectionTestUtils.setField(studentService, "errorCode", errorCode);
        StudentDto request = MockData.mapperStudent();

        when(repository.findById(request.getId()))
                .thenReturn(Mono.just(MockData.mapperStudentActivo()));

        StepVerifier.create(studentService.create(request))
                .expectError(StudentAlreadyExistsException.class)
                .verify();

        verify(repository, never()).create(any(Student.class));
    }

}
