package com.example.productcodes.domain;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "tipo_medicamento")
@Data
public class TipoMedicamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    private boolean activo = true;

}
