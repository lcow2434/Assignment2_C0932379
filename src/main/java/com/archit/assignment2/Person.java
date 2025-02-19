package com.archit.assignment2;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.io.IOException;

@Getter
@EqualsAndHashCode
@ToString
@Builder
public class Person {
    private String id;
    private String firstName;
    private String lastName;
    private Integer age;
    private String gender;

    private Person(String id, String firstName, String lastName, Integer age, String gender) {
        if (id == null || firstName == null || firstName.trim().isEmpty() || lastName == null || lastName.trim().isEmpty() || age < 0) {
            throw new IllegalArgumentException("Invalid input values");
        }
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.gender = gender;
    }

    public static Person fromJson(String json) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(json, Person.class);
    }
}

