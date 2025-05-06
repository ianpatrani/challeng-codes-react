package com.example.productcodes.controller;

import com.example.productcodes.domain.Medicamento;
import com.example.productcodes.domain.TipoMedicamento;
import com.example.productcodes.service.MedicamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/medicamentos")
public class MedicamentoController {

    @Autowired
    private MedicamentoService service;

    @PostMapping("/tipo")
    public ResponseEntity<TipoMedicamento> crearTipo(@RequestParam String nombre) {
        return ResponseEntity.ok(service.crearTipo(nombre));
    }

    @DeleteMapping("/tipo/{id}")
    public ResponseEntity<?> bajaLogicaTipo(@PathVariable Long id) {
        service.bajaLogicaTipo(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/")
    public ResponseEntity<Medicamento> crearMedicamento(@RequestBody Medicamento med) {
        return ResponseEntity.ok(service.crearMedicamento(med));
    }

    @GetMapping("/tipo/{tipo}")
    public ResponseEntity<List<Medicamento>> listarPorTipo(@PathVariable String tipo) {
        return ResponseEntity.ok(service.listarPorTipo(tipo));
    }

    @GetMapping("/nombre/{letra}")
    public ResponseEntity<List<Medicamento>> listarPorLetra(@PathVariable String letra) {
        return ResponseEntity.ok(service.listarNombreComercialEmpiezaCon(letra));
    }

    @GetMapping("/tipos")
    public ResponseEntity<List<Medicamento>> listarTipos() {
        return ResponseEntity.ok(service.listarTipos());
    }
}

