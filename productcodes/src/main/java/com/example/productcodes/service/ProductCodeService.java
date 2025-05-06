package com.example.productcodes.service;

import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ProductCodeService {

    public boolean esPrioritario(String codigo) {
        if (codigo == null || codigo.length() < 1) return false;
        String prefix = codigo.split("-")[0];
        return prefix.startsWith("P") || prefix.startsWith("W");
    }

    public boolean verificar(String codigo) {
        try {
            String[] parts = codigo.split("-");
            String region = parts[1];
            int verificadorReal = Character.getNumericValue(codigo.charAt(codigo.length() - 1));

            int suma = 0;
            for (char c : region.toCharArray()) {
                suma += Character.getNumericValue(c);
            }

            while (suma >= 10) {
                int temp = 0;
                while (suma > 0) {
                    temp += suma % 10;
                    suma /= 10;
                }
                suma = temp;
            }

            return suma == verificadorReal;
        } catch (Exception e) {
            return false;
        }
    }

    public List<String> ordenar(List<String> codigos) {
        return codigos.stream()
                .sorted(Comparator.comparing(c -> c.split("-")[0]))
                .collect(Collectors.toList());
    }

    public List<String> union(List<String> lista1, List<String> lista2) {
        Set<String> set = new HashSet<>(lista1);
        set.addAll(lista2);
        return new ArrayList<>(set);
    }

    public List<String> interseccion(List<String> lista1, List<String> lista2) {
        Set<String> set1 = new HashSet<>(lista1);
        set1.retainAll(lista2);
        return new ArrayList<>(set1);
    }
}