package com.example.inventarioVacunas.model.entities;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Data
@Entity
@Table(name = "ADMINISTRATOR")
public class Administrator extends Person implements Serializable {
}
