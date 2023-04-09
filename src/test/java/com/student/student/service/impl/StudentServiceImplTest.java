package com.student.student.service.impl;

import com.student.student.constant.StatusStudent;
import com.student.student.exception.NotFoundException;
import com.student.student.exception.StudentAlreadyExistsException;
import com.student.student.mock.MockData;
import com.student.student.model.StudentDto;
import com.student.student.repository.StudentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.util.ReflectionTestUtils;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
public class StudentServiceImplTest {

    @Mock
    private StudentRepository repository;

    @InjectMocks
    private StudentServiceImpl studentService;

    @BeforeEach
    void setUp() {
        ReflectionTestUtils.setField(studentService, "errorMessage", "El estudiante con ID %s ya existe");
        ReflectionTestUtils.setField(studentService, "errorCode", "ERR001");
        ReflectionTestUtils.setField(studentService, "emptyMessage", "No se encontro resultados");
        ReflectionTestUtils.setField(studentService, "emptyCode", "ERR002");
    }

    @Test
    public void whenCreateStudentOk() {
        StudentDto request = MockData.mapperStudentRequest();
        when(repository.findById(request.getId()))
                .thenReturn(Mono.empty())
                .thenReturn(Mono.just(MockData.mapperStudentActivo()));
        when(repository.create(MockData.mapperStudentActivo()))
                .thenReturn(Mono.empty());
        StepVerifier.create(studentService.create(request))
                .expectNext(MockData.mapperStudentResponse())
                .verifyComplete();
        verify(repository, times(2)).findById(request.getId());
        verify(repository, times(1)).create(MockData.mapperStudentActivo());
    }

    @Test
    public void whenCreateStudentAlreadyExists() {
        StudentDto request = MockData.mapperStudentRequest();
        when(repository.findById(request.getId()))
                .thenReturn(Mono.just(MockData.mapperStudentActivo()));
        when(repository.create(any()))
                .thenReturn(Mono.empty());
        StepVerifier.create(studentService.create(request))
                .expectError(StudentAlreadyExistsException.class)
                .verify();
        verify(repository, times(1)).findById(request.getId());
        verify(repository, times(1)).create(any());
        verify(repository, times(1)).findById(request.getId());
    }

    @Test
    public void whenGetAllOk() {
        when(repository.findAllByStatus(StatusStudent.ACTIVO.name()))
                .thenReturn(Flux.fromIterable(List.of(MockData.mapperStudentActivo())));
        StepVerifier.create(studentService.getAll())
                .expectNextSequence(List.of(MockData.mapperStudentResponse()))
                .verifyComplete();
        verify(repository, times(1)).findAllByStatus(StatusStudent.ACTIVO.name());
    }

    @Test
    public void whenGetAllEmptyData() {
        when(repository.findAllByStatus(StatusStudent.ACTIVO.name()))
                .thenReturn(Flux.empty());
        StepVerifier.create(studentService.getAll())
                .expectError(NotFoundException.class)
                .verify();
        verify(repository, times(1)).findAllByStatus(StatusStudent.ACTIVO.name());
    }

}
