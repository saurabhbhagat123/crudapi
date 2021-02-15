package com.rest.controller;

import com.rest.entity.Person;
import com.rest.service.PersonService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class PersonController {

    @Autowired
    private PersonService personService;

    @PostMapping("/persons")
    @ApiOperation(value="Save person", authorizations=@Authorization(value="oauth2schema", scopes=@AuthorizationScope(description="write", scope="resource-server-write")))
    public ResponseEntity<Person> createPerson(@RequestBody Person person){
        Person personObj = personService.createPerson(person);
        return ResponseEntity.status(HttpStatus.CREATED).body(personObj);
    }

    @GetMapping("/persons/{id}")
    @ApiOperation(value="Get a Person by ID", authorizations=@Authorization(value="oauth2schema", scopes=@AuthorizationScope(description="read", scope="resource-server-read")))
    public ResponseEntity<Person> getPerson(@PathVariable("id")int id){

        Person person = personService.getPersonById(id);
       return ResponseEntity.status(HttpStatus.OK).body(person);
    }

    @PutMapping("/persons/{id}")
    @ApiOperation(value="Update a Person", authorizations=@Authorization(value="oauth2schema", scopes=@AuthorizationScope(description="write", scope="resource-server-write")))
    public ResponseEntity<Person> updatePerson(@RequestBody Person person,@PathVariable("id")int id){
        Person updatedPerson = personService.updatePerson(person, id);
        return ResponseEntity.status(HttpStatus.OK).body(updatedPerson);
    }

    @DeleteMapping("/persons/{id}")
    @ApiOperation(value="Delete Person by ID", authorizations=@Authorization(value="oauth2schema", scopes=@AuthorizationScope(description="write", scope="resource-server-write")))
    public ResponseEntity deletePerson(@PathVariable("id")int id){
        personService.deletePerson(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping("/persons")
    @ApiOperation(value="Get All Persons", authorizations=@Authorization(value="oauth2schema", scopes=@AuthorizationScope(description="read", scope="resource-server-read")))
    public ResponseEntity<List<Person>> getAllPersons(){

        List<Person> personList = personService.getAllPerson();
        return ResponseEntity.status(HttpStatus.OK).body(personList);
    }

    @GetMapping("/test")
    public String test(){
        return "Working...";
    }
}
