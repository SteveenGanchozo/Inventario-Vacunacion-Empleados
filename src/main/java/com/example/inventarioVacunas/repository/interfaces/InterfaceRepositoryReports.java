package com.example.inventarioVacunas.repository.interfaces;

import com.example.inventarioVacunas.model.entities.Employee;
import com.example.inventarioVacunas.model.enums.VaccineType;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface InterfaceRepositoryReports {
    List<Employee> getEmployeeVaccinationType(VaccineType vaccine);
    List<Employee> getEmployeByDate(Date startDate, Date endDate);
}
