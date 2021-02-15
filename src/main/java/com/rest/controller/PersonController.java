package com.rest.controller;

import com.rest.entity.Person;
import com.rest.service.PersonService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class PersonController {

    @Autowired
    private PersonService personService;

    @PostMapping("/persons")
    @ApiOperation(value="Save persons", authorizations=@Authorization(value="oauth2schema", scopes=@AuthorizationScope(description="write", scope="resource-server-write")))
    public List<Person> createPerson(@RequestBody List<Person> persons){
        return personService.createPerson(persons);
    }

    @GetMapping("/persons/{id}")
    @ApiOperation(value="Get a Person by ID", authorizations=@Authorization(value="oauth2schema", scopes=@AuthorizationScope(description="read", scope="resource-server-read")))
    public Person getPerson(@PathVariable("id")int id){
        return personService.getPersonById(id);
    }

    @PutMapping("/persons/{id}")
    @ApiOperation(value="Update a Person", authorizations=@Authorization(value="oauth2schema", scopes=@AuthorizationScope(description="write", scope="resource-server-write")))
    public Person updatePerson(@RequestBody Person person,@PathVariable("id")int id){
        return personService.updatePerson(person,id);
    }

    @DeleteMapping("/persons/{id}")
    @ApiOperation(value="Delete Person by ID", authorizations=@Authorization(value="oauth2schema", scopes=@AuthorizationScope(description="write", scope="resource-server-write")))
    public int deletePerson(@PathVariable("id")int id){
        return personService.deletePerson(id);
    }

    @GetMapping("/persons")
    @ApiOperation(value="Get All Persons", authorizations=@Authorization(value="oauth2schema", scopes=@AuthorizationScope(description="read", scope="resource-server-read")))
    public List<Person> getAllPersons(){
        return personService.getAllPerson();
    }

    @GetMapping("/test")
    public String test(){
        return "Working...";
    }
}
