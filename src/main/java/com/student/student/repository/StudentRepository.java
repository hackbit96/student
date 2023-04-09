package com.student.student.repository;

import com.student.student.entity.Student;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface StudentRepository extends ReactiveCrudRepository<Student, Long> {

    @Query("INSERT INTO student (id, name, last_name, age, status) VALUES (:#{#student.id}, :#{#student.name}, :#{#student.lastName}, :#{#student.age}, :#{#student.status})")
    Mono<Void> create(@Param("student") Student student);

    Flux<Student> findAllByStatus(String status);

}
