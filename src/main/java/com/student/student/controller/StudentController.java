package com.student.student.controller;

import com.student.student.model.ResponseError;
import com.student.student.model.StudentDto;
import com.student.student.service.StudentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@RestController
@RequestMapping("v1/student")
@Tag(name = "Student", description = "Student Controller")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @Operation(summary = "Servicio para crear un Alumno")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Los datos del Alumno se crearon",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = StudentDto.class))}),
            @ApiResponse(responseCode = "409",
                    description = "Los datos del Alumno ya se encuentran registrados",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ResponseError.class)))
    })
    @PostMapping
    public Mono<StudentDto> create(@Valid @RequestBody StudentDto student) {
        return studentService.create(student);
    }

    @Operation(summary = "Servicio para obtener un Alumno")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Los obtuvieron todos los alumnos con estado activo",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = StudentDto.class))}),
            @ApiResponse(responseCode = "404",
                    description = "No hay ningun alumno activo",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ResponseError.class)))
    })
    @GetMapping
    public Flux<StudentDto> getAll() {
        return studentService.getAll();
    }

}
