package com.rest.controller;

import com.rest.controller.PersonController;
import com.rest.entity.Person;
import com.rest.service.PersonService;
import org.bouncycastle.math.raw.Mod;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class PersonControllerTest {

    @Mock
    private PersonService personService;

    @InjectMocks
    private PersonController personController;

    @Test
    public void test_createPersons(){
        Person person = new Person();
        person.setId(1);
        person.setAge("21");
        person.setFavourite_colour("Blue");
        person.setFirst_name("Kunal");
        person.setLast_name("Kher");


        Mockito.when(personService.createPerson(person)).thenReturn(person);
        ResponseEntity<Person> responseEntity = personController.createPerson(person);
        Assert.assertEquals(person.getFirst_name(),responseEntity.getBody().getFirst_name());
    }

    @Test
    public void test_getPerson(){
        Person person1 = new Person();
        person1.setId(1);
        person1.setAge("21");
        person1.setFavourite_colour("Blue");
        person1.setFirst_name("Kunal");
        person1.setLast_name("Kher");

        Mockito.when(personService.getPersonById(Mockito.eq(1))).thenReturn(person1);
        ResponseEntity<Person> responseEntity = personController.getPerson(1);
        Assert.assertEquals(person1.getFirst_name(),responseEntity.getBody().getFirst_name());
    }

    @Test
    public void test_updatePerson(){
        Person person1 = new Person();
        person1.setId(1);
        person1.setAge("21");
        person1.setFavourite_colour("Blue");
        person1.setFirst_name("Deepak");
        person1.setLast_name("Kher");

        Mockito.when(personService.updatePerson(Mockito.any(Person.class),Mockito.eq(1))).thenReturn(person1);
        ResponseEntity<Person> responseEntity = personController.updatePerson(person1, 1);
        Assert.assertEquals(person1.getFirst_name(),responseEntity.getBody().getFirst_name());
    }


    @Test
    public void test_deletePerson(){
        Mockito.doNothing().when(personService).deletePerson(Mockito.eq(1));
        ResponseEntity responseEntity = personController.deletePerson(1);
        Assert.assertEquals(204,responseEntity.getStatusCodeValue());
    }

    @Test
    public void test_getAllPersons(){

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


        Mockito.when(personService.getAllPerson()).thenReturn(persons);
        ResponseEntity<List<Person>> responseEntity = personController.getAllPersons();
        Assert.assertEquals(persons.size(),responseEntity.getBody().size());

    }
}
