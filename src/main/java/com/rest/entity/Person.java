package com.rest.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "person")
@Data
public class Person {

    @Id
    @GeneratedValue
    private int id;

    @Column
    private String first_name;

    @Column
    private String last_name;


    @Column
    private String age;

    @Column
    private String favourite_colour;




}
