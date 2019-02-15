package com.github.rluisb.springwebflux.client;

import com.github.rluisb.springwebflux.model.Student;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

public class StudentClient {

    private WebClient client = WebClient.create("http://localhost:8081");

    private Mono<ClientResponse> result = client.get()
            .uri("/students")
            .accept(MediaType.APPLICATION_JSON)
            .exchange();

    public List<Student> getResult() {
        return result.flatMap(res -> res.bodyToMono(new ParameterizedTypeReference<List<Student>>() {})).block();
    }
}
