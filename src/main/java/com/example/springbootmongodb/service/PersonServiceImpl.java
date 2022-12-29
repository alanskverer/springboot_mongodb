package com.example.springbootmongodb.service;

import com.example.springbootmongodb.collection.Person;
import com.example.springbootmongodb.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PersonServiceImpl implements PersonService {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public String save(Person person) {
        return personRepository.save(person).getPersonId();
    }

    @Override
    public List<Person> findAll() {
        return personRepository.findAll();
    }

    @Override
    public List<Person> getPersonStartsWith(String name) {
        return personRepository.findByFirstNameStartsWith(name);
    }

    @Override
    public void delete(String id) {
        personRepository.deleteById(id);
    }

    @Override
    public List<Person> getByPersonAge(Integer minAge, Integer maxAge) {
        return personRepository.findPersonByAgeBetween(minAge, maxAge);
    }

    //Building my own query with pagination with mongoTemplate
    @Override
    public Page<Person> search(String name, Integer minAge, Integer maxAge, Pageable pageable) {
        Query query = new Query().with(pageable);
        List<Criteria> criteria = new ArrayList<>();

        if (name != null && !name.isEmpty()) {
            criteria.add(Criteria.where("firstName").regex(name, "i"));
        }

        if (minAge != null && maxAge != null) {
            criteria.add(Criteria.where("age").gte(minAge).lte(maxAge));
        }

        if (!criteria.isEmpty()) {
            query.addCriteria(new Criteria()
                    .andOperator(criteria.toArray(new Criteria[0])));
        }

        Page<Person> people = PageableExecutionUtils.getPage(
                mongoTemplate.find(query, Person.class
                ), pageable, ()-> mongoTemplate.count(query.skip(0).limit(0),Person.class)
        );
        return people;


    }


}
