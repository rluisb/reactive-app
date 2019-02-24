package com.github.rluisb.springwebflux.router;

import com.github.rluisb.springwebflux.handler.StudentHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;

@Configuration
public class StudentRouter {
    @Bean
    public RouterFunction<ServerResponse> route(StudentHandler studentHandler) {
        return RouterFunctions
                .route(GET("/students"), studentHandler::students)
                .andRoute(GET("/studentsTextStream"), studentHandler::studentsTextEventStream)
                .andRoute(GET("/studentsJsonStream"), studentHandler::studentsJsonEventStream);
    }
}
