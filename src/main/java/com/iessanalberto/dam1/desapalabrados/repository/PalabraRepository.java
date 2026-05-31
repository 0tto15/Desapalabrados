package com.iessanalberto.dam1.desapalabrados.repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class PalabraRepository {

    // Constantes
    private static List<String> listaPalabras;
    private static String palabraSeleccionada;

    public static String desarmarPalabra () {

        listaPalabras = new ArrayList<>();
        listaPalabras.add("perro");
        listaPalabras.add("caballo");
        listaPalabras.add("gato");

        // Generar indice
        Random random = new Random();
        int indiceAleatorio = random.nextInt(listaPalabras.size());
        palabraSeleccionada = listaPalabras.get(indiceAleatorio);

        // Desmandelar palabra
        List<Character> letras = new ArrayList<>();
        for (char c : palabraSeleccionada.toCharArray()) {
            letras.add(c);
        }

        // Desordenar letras
        Collections.shuffle(letras);

        // Lo pasmos a string
        StringBuilder palabraDesordenada = new StringBuilder();
        for (Character letra : letras) {
            palabraDesordenada.append(letra);
        }
        return palabraDesordenada.toString();
    }

    // Getter
    public static String getPalabraSeleccionada() {
        return palabraSeleccionada;
    }
}
