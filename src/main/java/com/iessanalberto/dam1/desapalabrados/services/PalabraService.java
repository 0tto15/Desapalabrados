package com.iessanalberto.dam1.desapalabrados.services;
import com.iessanalberto.dam1.desapalabrados.repository.PalabraRepository;

public class PalabraService {

    // Constantes
    private static int intentos = 3;

    //Repositorios
    private PalabraRepository palabraRepository;

    public String getPalabra() throws Exception {
       return palabraRepository.desarmarPalabra ();
    }

    public boolean comprobarPalabra (String palabra) throws Exception {
        boolean resultado = true;
        if (palabra.isBlank()) {
            throw new Exception("Todos los campos deben estar llenos");
        }
        if (!palabraRepository.getPalabraSeleccionada().equals(palabra)) {
            intentos = intentos - 1;
            resultado = false;
        }
        return resultado;
    }

    public void jugarDenuevo () {
        intentos = intentos + 3;
    }

    // Getter
    public static int getIntentos() {
        return intentos;
    }
}
