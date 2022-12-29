package com.example.springbootmongodb.collection;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Builder
@Document(collection = "person")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Person {

    @Id
    private String personId;
    private Integer age;

    public Person(String personId, Integer age, String firstName) {
        this.personId = personId;
        this.age = age;
        this.firstName = firstName;
    }

    private String firstName;
//    private List<Address> addresses;

}
