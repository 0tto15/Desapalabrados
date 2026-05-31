package com.iessanalberto.dam1.desapalabrados.repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PalabraRepository {
    // Final para que no sea reasignable
    private final List<String> palabras = new ArrayList<>(Arrays.asList(
            "perro","caballo","gato"
    ));

    public List<String> getPalabras() {
        return new ArrayList<>(palabras);
        // Devuele una copia de la lista de palabras
    }
}
