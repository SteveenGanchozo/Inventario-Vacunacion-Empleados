package com.example.inventarioVacunas.controller;

import com.example.inventarioVacunas.model.dto.EmployeeDTO;
import com.example.inventarioVacunas.model.dto.InformationEmployeeDTO;
import com.example.inventarioVacunas.model.enums.VaccineType;
import com.example.inventarioVacunas.service.interfaces.InterfaceServiceEmployee;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/employee/v1")
@CrossOrigin({"*"})
public class EmployeeController {

    @Autowired
    private InterfaceServiceEmployee serviceEmployee;
    //Crear empleado
    @ApiOperation("Create employee")
    @PostMapping(value = "EmployeeSave", headers = "Accept=application/json;charset=UTF-8")
    private ResponseEntity EmployeeSave(@RequestBody EmployeeDTO emp) {
        try {
            return new ResponseEntity<>(serviceEmployee.saveEmployee(emp), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    //Completar Informacion del Empleado
    @ApiOperation("CompleteInformation")
    @PostMapping(value = "completeInformation", headers = "Accept=application/json;charset=UTF-8")
    private ResponseEntity completeInformation(@RequestBody InformationEmployeeDTO emp) {
        try {
            return new ResponseEntity<>(serviceEmployee.completeAllInformationEmployee(emp), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    //Obtener todos los empleados
    @GetMapping(value = "getAllEmployee")
    private ResponseEntity getAllEmployee() {
        try {
            return new ResponseEntity<>(serviceEmployee.getAll(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    //Obtener Empleado por ID
    @GetMapping(value = "getEmployee/{id}")
    private ResponseEntity getEmployee(@PathVariable Long id) {
        try {
            return new ResponseEntity<>(serviceEmployee.getById(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    //Eliminar Empleado
    @DeleteMapping(value = "deleteEmployee/{id}")
    private ResponseEntity deleteEmployee(@PathVariable Long id) {
        try {
            return new ResponseEntity<>(serviceEmployee.deleteEmployee(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    //Obtener empleado por estado de vacunación
    @GetMapping(value = "getByVacunationStatus")
    private ResponseEntity getByVacunationStatus(@RequestParam boolean vacunationStatus) {
        try {
            return new ResponseEntity<>(serviceEmployee.getEmployeeByVaccinationStatus(vacunationStatus), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    //Obtener empleado por tipo de vacunación
    @GetMapping(value = "getEmployeeByVaccineType")
    private ResponseEntity getEmployeeByVaccineType(@RequestParam VaccineType vaccineType) {
        try {
            return new ResponseEntity<>(serviceEmployee.getEmployeeVaccineType(vaccineType), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    //Obtener empleado por fecha de vacunación
    @ApiImplicitParams({
            @ApiImplicitParam(name = "startDate", value = "Start Date", required = true, dataType = "Date", example = "2022-01-01"),
            @ApiImplicitParam(name = "endDate", value = "End Date", required = true, dataType = "Date", example = "2022-12-31")
    })
    @GetMapping(value = "getEmployeeByDate")
    private ResponseEntity getEmployeeByDate(@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate, @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate) {
        try {
            return new ResponseEntity<>(serviceEmployee.getEmployeeByDate(startDate, endDate), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
