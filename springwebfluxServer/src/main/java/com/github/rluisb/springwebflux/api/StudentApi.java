package com.github.rluisb.springwebflux.api;

import com.github.rluisb.springwebflux.model.Student;
import com.github.rluisb.springwebflux.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
public class StudentApi {

    private StudentService studentService;

    @Autowired
    public StudentApi(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping(value = "/studentsApi")
    public Flux<Student> getAllStudents() {
        return studentService.getAllStudents();
    }

    @GetMapping(value = "/studentsTextStreamApi",
            produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Student> getAllStudentsTextStream() {
        return studentService.getAllStudents();
    }

    @GetMapping(value = "/studentsJsonStreamApi",
            produces = MediaType.APPLICATION_STREAM_JSON_VALUE)
    public Flux<Student> getAllStudentsJsonStream() {
        return studentService.getAllStudents();
    }

}
