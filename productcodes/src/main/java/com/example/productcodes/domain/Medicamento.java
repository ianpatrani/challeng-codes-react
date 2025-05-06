package com.example.productcodes.domain;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "medicamento")
@Data
public class Medicamento {

    @Id
    private Long codigo;

    private String nombreComercial;

    private String droga;

    @ManyToOne
    @JoinColumn(name = "tipo_id")
    private TipoMedicamento tipo;

}
