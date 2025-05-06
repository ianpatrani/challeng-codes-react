package com.example.productcodes.repositories;

import com.example.productcodes.domain.TipoMedicamento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TipoMedicamentoRepository extends JpaRepository<TipoMedicamento, Long> {
    List<TipoMedicamento> findByActivoTrue();
}

