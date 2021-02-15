package com.rest.service;

import com.rest.entity.Person;
import com.rest.repository.PersonRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(MockitoJUnitRunner.class)
public class PersonServiceTest {

    @Mock
    private PersonRepository personRepository;

    @InjectMocks
    private PersonService personService;


    @Test
    public void test_createPerson() {

        Person person1 = new Person();
        person1.setId(1);
        person1.setAge("21");
        person1.setFavourite_colour("Blue");
        person1.setFirst_name("Kunal");
        person1.setLast_name("Kher");


        Person person2 = new Person();
        person2.setId(2);
        person2.setAge("22");
        person2.setFavourite_colour("Green");
        person2.setFirst_name("Deepak");
        person2.setLast_name("Kool");

        List<Person> persons = new ArrayList<>();
        persons.add(person1);
        persons.add(person2);

        Mockito.when(personRepository.saveAll(Mockito.any())).thenReturn(persons);
        List<Person> personList = personService.createPerson(persons);
        Assert.assertEquals(persons.size(),personList.size());
    }

    @Test
    public void getPersonById() {
        Person person1 = new Person();
        person1.setId(1);
        person1.setAge("21");
        person1.setFavourite_colour("Blue");
        person1.setFirst_name("Kunal");
        person1.setLast_name("Kher");

        Mockito.when(personRepository.findById(Mockito.eq(1))).thenReturn(Optional.of(person1));
        Person personById = personService.getPersonById(1);
        Assert.assertEquals(person1.getFirst_name(),personById.getFirst_name());
    }

    @Test
    public void updatePerson() {
        Person person1 = new Person();
        person1.setId(1);
        person1.setAge("21");
        person1.setFavourite_colour("Blue");
        person1.setFirst_name("Kunal");
        person1.setLast_name("Kher");

        Mockito.when(personRepository.findById(Mockito.eq(1))).thenReturn(Optional.of(person1));
        Mockito.when(personRepository.save(Mockito.any(Person.class))).thenReturn(person1);

        Person person = personService.updatePerson(person1, 1);
        Assert.assertEquals(person1.getFirst_name(),person.getFirst_name());
    }

    @Test
    public void deletePerson() {

        Mockito.doNothing().when(personRepository).deleteById(Mockito.eq(1));
        int id = personService.deletePerson(1);
        Assert.assertEquals(1,id);
    }

    @Test
    public void getAllPerson() {
        Person person1 = new Person();
        person1.setId(1);
        person1.setAge("21");
        person1.setFavourite_colour("Blue");
        person1.setFirst_name("Kunal");
        person1.setLast_name("Kher");


        Person person2 = new Person();
        person2.setId(2);
        person2.setAge("22");
        person2.setFavourite_colour("Green");
        person2.setFirst_name("Deepak");
        person2.setLast_name("Kool");

        List<Person> persons = new ArrayList<>();
        persons.add(person1);
        persons.add(person2);

        Mockito.when(personRepository.findAll()).thenReturn(persons);
        List<Person> allPerson = personService.getAllPerson();
        Assert.assertEquals(persons.size(),allPerson.size());
    }
}