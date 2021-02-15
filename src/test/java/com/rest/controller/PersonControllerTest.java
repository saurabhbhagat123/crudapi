package com.rest.controller;

import com.rest.controller.PersonController;
import com.rest.entity.Person;
import com.rest.service.PersonService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

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

        Mockito.when(personService.createPerson(persons)).thenReturn(persons);
        List<Person> personList = personController.createPerson(persons);
        Assert.assertEquals(persons.size(),personList.size());
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
        Person person = personController.getPerson(1);
        Assert.assertEquals(person1.getFirst_name(),person.getFirst_name());
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
        Person person = personController.updatePerson(person1, 1);
        Assert.assertEquals(person1.getFirst_name(),person.getFirst_name());
    }


    @Test
    public void test_deletePerson(){
        Mockito.when(personService.deletePerson(Mockito.eq(1))).thenReturn(1);
        int id = personController.deletePerson(1);
        Assert.assertEquals(1,id);
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
        List<Person> allPersons = personController.getAllPersons();
        Assert.assertEquals(persons.size(),allPersons.size());

    }
}
