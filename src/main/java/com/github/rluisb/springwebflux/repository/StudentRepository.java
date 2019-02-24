package com.github.rluisb.springwebflux.repository;

import com.github.rluisb.springwebflux.model.Student;
import io.reactivex.Flowable;
import org.davidmoten.rx.jdbc.ConnectionProvider;
import org.davidmoten.rx.jdbc.Database;
import org.davidmoten.rx.jdbc.pool.NonBlockingConnectionPool;
import org.davidmoten.rx.jdbc.pool.Pools;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.jooq.impl.DSL.field;

@Component
public class StudentRepository {

    private Database database;

    @Autowired
    public StudentRepository(DataSource dataSource) throws SQLException {
        Connection connection = dataSource.getConnection();
        NonBlockingConnectionPool nonBlockingConnectionPool =
                Pools.nonBlocking()
                        .maxPoolSize(Runtime.getRuntime().availableProcessors() * 5)
                        .connectionProvider(ConnectionProvider.from(connection))
                        .build();
        this.database = Database.from(nonBlockingConnectionPool);
    }

    public Flux<Student> getAllStudents() {
        String query = "SELECT * FROM student";

        Flowable<Student> students =
                database.select(query)
                        .get(resultSet -> Student.builder()
                                .id(resultSet.getInt("id"))
                                .name(resultSet.getString("name"))
                                .age(resultSet.getInt("age"))
                                .build());

        return Flux.from(students);
    }
}