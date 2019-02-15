package com.github.rluisb.springwebflux.model;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@EqualsAndHashCode
@Getter
@Setter
public class Student {

    private Integer id;
    private String name;
    private Integer age;
}
