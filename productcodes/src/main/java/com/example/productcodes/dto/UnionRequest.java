package com.example.productcodes.dto;

import java.util.List;

public class UnionRequest {
    private List<String> lista1;
    private List<String> lista2;

    public List<String> getLista1() {
        return lista1;
    }

    public void setLista1(List<String> lista1) {
        this.lista1 = lista1;
    }

    public List<String> getLista2() {
        return lista2;
    }

    public void setLista2(List<String> lista2) {
        this.lista2 = lista2;
    }
}
