package com.student.student.service.impl;

import com.student.student.constant.StatusStudent;
import com.student.student.exception.NotFoundException;
import com.student.student.exception.StudentAlreadyExistsException;
import com.student.student.model.StudentDto;
import com.student.student.repository.StudentRepository;
import com.student.student.service.MapperUtil;
import com.student.student.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository repository;

    @Value("${student.create.message}")
    private String errorMessage;

    @Value("${student.create.code}")
    private String errorCode;

    @Value("${student.empty.message}")
    private String emptyMessage;

    @Value("${student.empty.code}")
    private String emptyCode;

    @Override
    public Mono<StudentDto> create(StudentDto studentDTO) {
        return repository.findById(studentDTO.getId())
                .flatMap(existingStudent -> responseException(studentDTO))
                .switchIfEmpty(repository.create(MapperUtil.mapperStudentCreate(studentDTO))
                        .then(Mono.just(studentDTO))
                        .flatMap(studentDto -> repository.findById(studentDto.getId()))
                        .map(MapperUtil::mapperStudent))
                .doOnNext(student -> log.info("CREATED STUDENT: {}", student));
    }

    @Override
    public Flux<StudentDto> getAll() {
        return repository.findAllByStatus(StatusStudent.ACTIVO.name())
                .switchIfEmpty(Mono.error(new NotFoundException(emptyMessage, emptyCode)))
                .map(MapperUtil::mapperStudent)
                .doOnNext(student -> log.info("RETRIEVED STUDENT: {}", student));
    }

    private Mono<StudentDto> responseException(StudentDto studentDTO) {
        return Mono.error(new StudentAlreadyExistsException(
                String.format(errorMessage, studentDTO.getId()), errorCode));
    }

}
