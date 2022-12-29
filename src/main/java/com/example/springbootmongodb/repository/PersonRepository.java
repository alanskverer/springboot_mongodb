package com.example.springbootmongodb.repository;

import com.example.springbootmongodb.collection.Person;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonRepository extends MongoRepository<Person,String> {
    List<Person> findByFirstNameStartsWith(String name);

    //Auto query method
//    List<Person> findByAgeBetween(Integer min, Integer max);

    //Building a query + omitting the age property from the response
    @Query(value = "{ 'age' : { $gt: ?0, $lt : ?1}}", fields = "{age: 0}" )
    List<Person> findPersonByAgeBetween(Integer min, Integer max);
}
