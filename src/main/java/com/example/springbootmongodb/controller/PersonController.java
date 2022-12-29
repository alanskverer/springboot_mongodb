package com.example.springbootmongodb.controller;

import com.example.springbootmongodb.collection.Person;
import com.example.springbootmongodb.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/person")
public class PersonController {

    @Autowired
    private PersonService personService;

    @GetMapping
    public List<Person> test() {
        return personService.findAll();
    }

//    @GetMapping("")
//    public List<Person> getPersonStartsWith(@RequestParam("name") String name) {
//        return personService.getPersonStartsWith(name);
//    }

    @PostMapping
    public String save(@RequestBody Person person) {
        return personService.save(person);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        personService.delete(id);
    }

    @GetMapping("/age")
    public List<Person> getByPersonAge(
            @RequestParam Integer minAge,
            @RequestParam Integer maxAge
    ) {
        return personService.getByPersonAge(minAge, maxAge);
    }

    @GetMapping("/search")
    public Page<Person> searchPerson(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) Integer minAge,
            @RequestParam(required = false) Integer maxAge,
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "5") Integer size
            ) {
        Pageable pageable
                = PageRequest.of(page,size);
        return personService.search(name,minAge,maxAge,pageable);
    }



}
