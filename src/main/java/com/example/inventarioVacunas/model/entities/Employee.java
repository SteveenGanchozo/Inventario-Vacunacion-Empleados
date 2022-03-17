package com.example.inventarioVacunas.model.entities;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@Table(name = "EMPLOYEE")
public class Employee extends Person implements Serializable {
    @Column(name = "VACCINATION_STATUS")
    private Boolean vaccinationStatus;

    @Column(name = "BIRTH_DATE")
    private Date birthDate;
    private String address;
    private String phone;

    public Employee() {
    }

    public Employee(Long id, String dni, String name, String lastname, String email, Date birthDate) {
        super.setId(id);
        super.setName(name);
        super.setLastname(lastname);
        super.setDni(dni);
        super.setEmail(email);
        this.birthDate = birthDate;
    }
}
