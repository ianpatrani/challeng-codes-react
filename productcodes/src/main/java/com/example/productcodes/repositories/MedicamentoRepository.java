package com.example.productcodes.repositories;

import com.example.productcodes.domain.Medicamento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MedicamentoRepository extends JpaRepository<Medicamento, Long> {
    List<Medicamento> findByTipoNombreIgnoreCase(String nombreTipo);
    List<Medicamento> findByNombreComercialStartingWithIgnoreCase(String letra);
}