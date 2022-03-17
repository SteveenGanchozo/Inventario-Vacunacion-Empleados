package com.example.inventarioVacunas.service.interfaces;

import com.example.inventarioVacunas.model.entities.Employee;
import com.example.inventarioVacunas.model.dto.EmployeeDTO;
import com.example.inventarioVacunas.model.dto.InformationEmployeeDTO;
import com.example.inventarioVacunas.model.enums.VaccineType;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public interface InterfaceServiceEmployee {
    Employee saveEmployee(EmployeeDTO employeeDTO);
    Employee completeAllInformationEmployee(InformationEmployeeDTO employee);
    Long deleteEmployee(Long Id);
    Optional<Employee> getById(Long id);
    List<Employee> getAll();
    List<Employee> getEmployeeByVaccinationStatus(boolean vaccinationStatus);
    List<Employee> getEmployeeVaccineType(VaccineType vaccine);
    List<Employee> getEmployeeByDate(Date startDate, Date endDate);
}
