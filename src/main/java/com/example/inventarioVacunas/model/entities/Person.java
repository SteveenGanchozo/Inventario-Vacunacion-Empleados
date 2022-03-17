package com.example.inventarioVacunas.model.entities;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@Data
@MappedSuperclass
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "Generator")
    private Long id;
    private String name;
    private String lastname;
    private String dni;
    private String email;
    private String users;
    private String password;
}
