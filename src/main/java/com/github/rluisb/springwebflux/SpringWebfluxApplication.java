package com.github.rluisb.springwebflux;

import com.github.rluisb.springwebflux.client.StudentClient;
import com.github.rluisb.springwebflux.repository.StudentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringWebfluxApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringWebfluxApplication.class, args);
        StudentClient studentClient = new StudentClient();
        System.out.println(studentClient.getResult());
    }

}

