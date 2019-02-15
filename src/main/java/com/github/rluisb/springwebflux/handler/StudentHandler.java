package com.github.rluisb.springwebflux.handler;

import com.github.rluisb.springwebflux.model.Student;
import com.github.rluisb.springwebflux.repository.StudentRepository;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class StudentHandler {

    private final StudentRepository studentRepository;

    public StudentHandler(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Mono<ServerResponse> students(ServerRequest request) {
        Flux<Student> students = studentRepository.getAllStudents();
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(students, Student.class);
    }
}
