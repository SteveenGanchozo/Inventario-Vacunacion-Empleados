package com.example.inventarioVacunas.service;

import com.example.inventarioVacunas.model.dto.EmployeeDTO;
import com.example.inventarioVacunas.model.dto.InformationEmployeeDTO;
import com.example.inventarioVacunas.model.entities.Employee;
import com.example.inventarioVacunas.model.enums.VaccineType;
import com.example.inventarioVacunas.repository.interfaces.InterfaceRepositoryEmployee;
import com.example.inventarioVacunas.repository.interfaces.InterfaceRepositoryReports;
import com.example.inventarioVacunas.repository.interfaces.InterfaceRepositoryVaccine;
import com.example.inventarioVacunas.service.interfaces.InterfaceServiceEmployee;
import com.example.inventarioVacunas.utils.Utilies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ServiceEmployee implements InterfaceServiceEmployee {

    @Autowired
    private InterfaceRepositoryEmployee repositoryEmployee;
    @Autowired
    private InterfaceRepositoryReports repositoryReports;
    @Autowired
    private InterfaceRepositoryVaccine repositoryVaccine;

    //Guardar Epmpleado
    @Override
    public Employee saveEmployee(EmployeeDTO employeeDTO) {
        if (employeeDTO.getName() == null || !Utilies.isText(employeeDTO.getName()))
            throw new IllegalStateException("Invalid name");

        if (employeeDTO.getLastName() == null || !Utilies.isText(employeeDTO.getLastName()))
            throw new IllegalStateException("Invalid lastname");

        if (employeeDTO.getDni() == null || !Utilies.isValidDNI(employeeDTO.getDni()))
            throw new IllegalStateException("Invalid DNI");

        if (employeeDTO.getEmail() == null || !Utilies.isValidEmail(employeeDTO.getEmail()))
            throw new IllegalStateException("Invalid email");

        Employee emp = empleadoDTOtoEmpleado(employeeDTO);
        emp.setUsers(emp.getEmail());
        emp.setPassword(Utilies.generatePassword());

        return repositoryEmployee.save(emp);
    }

    //Eliminar Empleado
    @Override
    public Long deleteEmployee(Long id) {
        repositoryEmployee.deleteById(id);
        return id;
    }

    //Completar la información del empleado
    @Override
    public Employee completeAllInformationEmployee(InformationEmployeeDTO employee) {
        if (employee.getId() == null)
            throw new IllegalStateException("Employee ID is required");

        if (employee.getAddress() == null || employee.getAddress().trim().isEmpty())
            throw new IllegalStateException("Address is required");

        if (employee.getPhone() == null || employee.getPhone().trim().isEmpty())
            throw new IllegalStateException("Phone is required");

        if (employee.getVaccinationStatus() == null)
            throw new IllegalStateException("Vaccination Status is required");

        if (employee.getVaccinationStatus() == true && (employee.getVaccine() == null || employee.getVaccine().size() == 0))
            throw new IllegalStateException("Details of vaccination status is required");

        Optional<Employee> result = repositoryEmployee.findById(employee.getId());
        if (!result.isPresent())
            throw new IllegalStateException("Employee ID does not exist" + employee.getId());

        Employee empUpdate = result.get();
        empUpdate.setBirthDate(employee.getBirthDate());
        empUpdate.setAddress(employee.getAddress());
        empUpdate.setPhone(employee.getPhone());
        empUpdate.setVaccinationStatus(employee.getVaccinationStatus());

        if (employee.getVaccinationStatus() == true) {
            employee.getVaccine().forEach(e -> {
                e.setIdEmployee(empUpdate.getId());
                repositoryVaccine.save(e);
            });
        }
        return repositoryEmployee.save(empUpdate);
    }

    //Obtener todos los empleados
    @Override
    public List<Employee> getAll() {
        return repositoryEmployee.findAll();
    }

    //Obtener empleado por ID
    @Override
    public Optional<Employee> getById(Long id) {
        return repositoryEmployee.findById(id);
    }

    //obtener empleado por el estado de vacunación
    @Override
    public List<Employee> getEmployeeByVaccinationStatus(boolean vacunationStatus) {
        return repositoryEmployee.findByVaccinationStatus(vacunationStatus);
    }

    //obtener empleado por la fecha de vacunación
    @Override
    public List<Employee> getEmployeeByDate(Date startDate, Date endDate) {
        return repositoryReports.getEmployeByDate(startDate, endDate);
    }

    //obtener empleado por el tipo de vacuna
    @Override
    public List<Employee> getEmployeeVaccineType(VaccineType vaccine) {
        return repositoryReports.getEmployeeVaccinationType(vaccine);
    }


    //Convertir empleadoDTO a empleado
    private Employee empleadoDTOtoEmpleado(EmployeeDTO dto) {
        Employee employee = new Employee();
        employee.setName(dto.getName());
        employee.setLastname(dto.getLastName());
        employee.setDni(dto.getDni());
        employee.setEmail(dto.getEmail());
        employee.setVaccinationStatus(Boolean.FALSE);
        return employee;
    }
}