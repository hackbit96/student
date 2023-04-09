package com.student.student.controller;

import com.student.student.mock.MockData;
import com.student.student.model.StudentDto;
import com.student.student.service.StudentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.r2dbc.connection.init.ConnectionFactoryInitializer;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.ConstraintViolationException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.when;

@WebFluxTest(StudentController.class)
class StudentControllerTest {

    @Autowired
    private WebTestClient webTestClient;

    @MockBean
    private StudentService studentService;

    @MockBean
    private ConnectionFactoryInitializer connectionFactoryInitializer;

    @Test
    public void create_WithValidStudent_ReturnsStudent() {
        when(studentService.create(MockData.mapperStudentResponse()))
                .thenReturn(Mono.just(MockData.mapperStudentResponse()));
        webTestClient.post()
                .uri("/v1/student")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(MockData.mapperStudentResponse())
                .exchange()
                .expectStatus().isOk()
                .expectBody(StudentDto.class)
                .isEqualTo(MockData.mapperStudentResponse());
    }

    @Test
    public void create_WithInvalidStudent_ReturnsBadRequest() {
        StudentDto inputStudent = MockData.mapperStudentBadRequest();
        when(studentService.create(inputStudent))
                .thenThrow(new ConstraintViolationException(Collections.emptySet()));
        webTestClient.post()
                .uri("/v1/student")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(inputStudent)
                .exchange()
                .expectStatus().isBadRequest();
    }

    @Test
    public void getAll_ReturnsListOfStudent() {
        List<StudentDto> outputStudents = Arrays.asList(
                MockData.mapperStudentResponse(),
                MockData.mapperStudentResponse()
        );
        when(studentService.getAll())
                .thenReturn(Flux.fromIterable(outputStudents));
        webTestClient.get()
                .uri("/v1/student")
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(StudentDto.class)
                .isEqualTo(outputStudents);
    }

}