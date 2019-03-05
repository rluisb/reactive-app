package com.github.rluisb.springwebfluxclient;

import com.github.rluisb.springwebfluxclient.model.Student;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONString;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootApplication
public class SpringwebfluxclientApplication {

    @Bean
    WebClient getWebClient() {
        return WebClient.create("http://localhost:8081");
    }

    @Bean
    CommandLineRunner commandLineRunner(WebClient client) {
        return args -> {
            client.get()
                    .uri("/studentsJsonStreamApi")
                    .accept(MediaType.APPLICATION_STREAM_JSON)
                    .exchange()
                    .flatMapMany(clientResponse -> clientResponse.bodyToFlux(String.class))
                    .map(JSONObject::new)
                    .subscribe(student -> {
                        System.out.println("OLD STUDENT -> " + student);
                        int studentAge = student.getInt("age");
                        studentAge = studentAge + 120;
                        student.put("age", studentAge);
                        System.out.println("NEW STUDENT -> " + student);
                    });
        };
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringwebfluxclientApplication.class, args);
    }

}
