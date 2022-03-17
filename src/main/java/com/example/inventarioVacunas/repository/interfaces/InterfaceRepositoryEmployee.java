package com.example.inventarioVacunas.repository.interfaces;

import com.example.inventarioVacunas.model.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InterfaceRepositoryEmployee extends JpaRepository<Employee, Long> {
    List<Employee> findByVaccinationStatus(boolean vaccinationStatus);
}
