package com.example.productcodes.service;

import com.example.productcodes.domain.Medicamento;
import com.example.productcodes.domain.TipoMedicamento;
import com.example.productcodes.repositories.MedicamentoRepository;
import com.example.productcodes.repositories.TipoMedicamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedicamentoService {

    @Autowired
    private MedicamentoRepository medicamentoRepo;

    @Autowired
    private TipoMedicamentoRepository tipoRepo;

    public Medicamento crearMedicamento(Medicamento med) {
        return medicamentoRepo.save(med);
    }

    public TipoMedicamento crearTipo(String nombre) {
        TipoMedicamento tipo = new TipoMedicamento();
        tipo.setNombre(nombre);
        return tipoRepo.save(tipo);
    }

    public void bajaLogicaTipo(Long id) {
        tipoRepo.findById(id).ifPresent(tipo -> {
            tipo.setActivo(false);
            tipoRepo.save(tipo);
        });
    }

    public List<Medicamento> listarPorTipo(String tipo) {
        return medicamentoRepo.findByTipoNombreIgnoreCase(tipo);
    }

    public List<Medicamento> listarNombreComercialEmpiezaCon(String letra) {
        return medicamentoRepo.findByNombreComercialStartingWithIgnoreCase(letra);
    }

    public List<Medicamento> listarTipos(){
        return medicamentoRepo.findAll();
    }
}
