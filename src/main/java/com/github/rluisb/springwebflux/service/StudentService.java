package com.github.rluisb.springwebflux.service;

import com.github.rluisb.springwebflux.model.Student;
import com.github.rluisb.springwebflux.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
public class StudentService {

    private StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Flux<Student> getAllStudents() {
        return studentRepository.getAllStudents();
    }
}
