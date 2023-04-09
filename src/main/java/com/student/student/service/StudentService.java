package com.student.student.service;

import com.student.student.model.StudentDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface StudentService {

    Mono<StudentDto> create(StudentDto student);

    Flux<StudentDto> getAll();

}
