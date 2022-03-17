package com.example.inventarioVacunas.model.dto;

import com.example.inventarioVacunas.model.entities.Vaccine;
import lombok.Data;

import javax.persistence.Transient;
import java.util.Date;
import java.util.List;

@Data
public class InformationEmployeeDTO {

    private Long id;
    private Boolean vaccinationStatus;
    private Date birthDate;
    private String address;
    private String phone;

    @Transient
    private List<Vaccine> vaccine;
}
