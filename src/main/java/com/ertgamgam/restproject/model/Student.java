package com.ertgamgam.restproject.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@AllArgsConstructor
@NoArgsConstructor
@Data //getter and setter implementation for
@Entity
@Table(name = "API.RestAPI.Student")
public class Student {

    //the @GeneratedValue annotation specifies that a value will be automatically generated for that field
    @Column(name = "Id")
    private @Id
    @GeneratedValue
    Long id;

    @Column(name = "Name", nullable = false)
    private String name;

    @Column(name = "Surname", nullable = false)
    private String surname;

    @Column(name = "studentNo", nullable =false)
    private int studentNo;

}
