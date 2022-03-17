package com.example.inventarioVacunas.repository.interfaces;

import com.example.inventarioVacunas.model.entities.Vaccine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InterfaceRepositoryVaccine extends JpaRepository<Vaccine, Long> {
}
