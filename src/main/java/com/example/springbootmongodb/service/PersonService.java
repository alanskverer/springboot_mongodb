package com.example.springbootmongodb.service;

import com.example.springbootmongodb.collection.Person;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PersonService {
    String save(Person person);


    List<Person> findAll();

    List<Person> getPersonStartsWith(String name);

    void delete(String id);

    List<Person> getByPersonAge(Integer minAge, Integer maxAge);

    Page<Person> search(String name, Integer minAge, Integer maxAge, Pageable pageable);
}
