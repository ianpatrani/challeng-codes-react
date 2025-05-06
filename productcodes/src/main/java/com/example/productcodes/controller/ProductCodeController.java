package com.example.productcodes.controller;

import com.example.productcodes.dto.UnionRequest;
import com.example.productcodes.service.ProductCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/codigos")
public class ProductCodeController {

    @Autowired
    private ProductCodeService service;

    @GetMapping("/prioritario")
    public boolean esPrioritario(@RequestParam String codigo) {
        return service.esPrioritario(codigo);
    }

    @GetMapping("/verificar")
    public boolean verificar(@RequestParam String codigo) {
        return service.verificar(codigo);
    }

    @PostMapping("/ordenar")
    public List<String> ordenar(@RequestBody List<String> codigos) {
        return service.ordenar(codigos);
    }

    @PostMapping("/union")
    public List<String> union(@RequestBody UnionRequest request) {
        return service.union(request.getLista1(), request.getLista2());
    }

    @PostMapping("/interseccion")
    public List<String> interseccion(@RequestBody UnionRequest request) {
        return service.interseccion(request.getLista1(), request.getLista2());
    }

}