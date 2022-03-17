package com.example.inventarioVacunas.repository;

import com.example.inventarioVacunas.model.entities.Employee;
import com.example.inventarioVacunas.model.enums.VaccineType;
import com.example.inventarioVacunas.repository.interfaces.InterfaceRepositoryReports;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class RepositoryReports implements InterfaceRepositoryReports {

    @Autowired
    private EntityManager entityManager;

    //Filtrar empleado por tipo de vacunación
    @Override
    public List<Employee> getEmployeeVaccinationType(VaccineType vaccine) {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT EMP.ID, EMP.DNI, EMP.NAME, EMP.LASTNAME, EMP.EMAIL, EMP.BIRTH_DATE ")
                .append("FROM EMPLOYEE EMP ").append("INNER JOIN VACCINE VAC ")
                .append("ON VAC.ID_EMPLOYEE = EMP.ID ").append("WHERE VAC.VACCINE = :vaccine");
        Query query = entityManager.createNativeQuery(sql.toString());
        query.setParameter("vaccine", vaccine.toString());

        return convertObjetToEmployee(query.getResultList());
    }

    //Filtrar empleado por fecha de vacunación
    @Override
    public List<Employee> getEmployeByDate(Date startDate, Date endDate) {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT EMP.ID, EMP.DNI, EMP.NAME, EMP.LASTNAME, EMP.EMAIL, EMP.BIRTH_DATE ")
                .append("FROM EMPLOYEE EMP ").append("INNER JOIN VACCINE VAC ")
                .append("ON VAC.ID_EMPLOYEE = EMP.ID ")
                .append("WHERE VAC.VACCINATION_DATE BETWEEN :startDate AND :endDate");
        Query query = entityManager.createNativeQuery(sql.toString());
        query.setParameter("startDate", startDate);
        query.setParameter("endDate", endDate);

        return convertObjetToEmployee(query.getResultList());
    }

    //Convertir lista de objetos a lista empleados
    public List<Employee> convertObjetToEmployee(List<Object[]> list) {
        List<Employee> result = list.stream()
                .map(e -> new Employee(((BigInteger) e[0]).longValue(), (String) e[1], (String) e[2], (String) e[3], (String) e[4], (Date) e[5]))
                .collect(Collectors.toList());
        return result;
    }
}
