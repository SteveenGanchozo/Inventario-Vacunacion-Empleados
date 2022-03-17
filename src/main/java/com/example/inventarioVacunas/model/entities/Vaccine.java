package com.example.inventarioVacunas.model.entities;

import com.example.inventarioVacunas.model.enums.VaccineType;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "VACCINE")
public class Vaccine {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "Generator")
    private Long id;

    @Column(name = "DOSES_NUMBER")
    private Integer dosesNum;

    @Column(name = "ID_EMPLOYEE")
    private Long idEmployee;

    @Enumerated(EnumType.STRING)
    private VaccineType vaccine;

    @Column(name = "VACCINATION_DATE")
    private Date vaccinationDate;

}
