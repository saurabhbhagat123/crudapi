package com.rest.service;

import com.rest.entity.Person;
import com.rest.exception.PersonAlreadyExistException;
import com.rest.exception.PersonNotFoundException;
import com.rest.repository.PersonRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class PersonService {



    @Autowired
    private PersonRepository personRepository;

    public Person createPerson(Person person){
        log.info("createPerson called ");
        Optional<Person> personOptional = personRepository.findById(person.getId());
        if(personOptional.isPresent()){
            throw new PersonAlreadyExistException("Person with ID "+person.getId()+" already exists");
        }
        return personRepository.save(person);
    }

    public Person getPersonById(int id){
        log.info("getPersonById called ");
        Optional<Person> personOptional = personRepository.findById(id);
        if(!personOptional.isPresent()){
            throw new PersonNotFoundException("Person with ID "+id+" not found");
        }
        return personOptional.get();
    }
    
    public Person updatePerson(Person person,int id){
        log.info("updatePerson called ");
        Optional<Person> personOptional = personRepository.findById(id);
        if(!personOptional.isPresent()){
            throw new PersonNotFoundException("Person with ID "+id+" not found");
        }

        person.setId(id);
        return personRepository.save(person);
    }

    public void deletePerson(int id){
        log.info("deletePerson called ");

        Optional<Person> personOptional = personRepository.findById(id);
        if(!personOptional.isPresent()){
            throw new PersonNotFoundException("Person with ID "+id+" not found");
        }

        personRepository.deleteById(id);
    }

    public List<Person> getAllPerson(){
        log.info("getAllPerson called ");
        return personRepository.findAll();
    }
}
