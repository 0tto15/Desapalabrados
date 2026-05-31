package com.iessanalberto.dam1.desapalabrados.services;
import com.iessanalberto.dam1.desapalabrados.models.Jugador;
import com.iessanalberto.dam1.desapalabrados.models.Partida;
import com.iessanalberto.dam1.desapalabrados.repository.PalabraRepository;

import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class PalabraService {
    private final PalabraRepository palabraRepository;
    private Partida partidaActual;

    // Inyección de dependencias por constructor (Esto es para mokito, hacer caso omiso)
    public PalabraService(PalabraRepository palabraRepository) {
        this.palabraRepository = palabraRepository;
        inicializarNuevaPartida("Jugador test");
    }

    // Desordenar palabra
    private String desordenarPalabra(String palabra) {
        List<Character> letras = palabra.chars()
                .mapToObj(c -> (char) c)
                .collect(Collectors.toList());

        Collections.shuffle(letras);

        StringBuilder palabraDesordenada = new StringBuilder();
        for (char letra : letras) {
            palabraDesordenada.append(letra);
        }
        return palabraDesordenada.toString();
    }

    public void inicializarNuevaPartida(String nombreJugador) {
        Jugador jugador = new Jugador(nombreJugador, 0);
        List<String> palabras = palabraRepository.getPalabras();

        // Seleccionamos una palabra al azar
        String palabraSecreta = palabras.get(new Random().nextInt(palabras.size()));
        String desordenada = desordenarPalabra(palabraSecreta);

        // Creamos la instancia del juego con su estado inicial
        this.partidaActual = new Partida(jugador, 3, palabraSecreta,desordenada,palabras);
    }

    public boolean comprobarPalabra(String palabraUsuario) throws Exception {
        if (palabraUsuario == null || palabraUsuario.isEmpty()) {
            throw new Exception("Por favor, introduce una palabra válida.");
        }
        String correcta = partidaActual.getPalabraSeleccionada();
        if (palabraUsuario.equalsIgnoreCase(correcta)) {

            // Si acierta, subimos la puntuación del jugador
            int puntuacionActual = partidaActual.getJugador().getPuntuacion();
            partidaActual.getJugador().setPuntuacion(puntuacionActual + 10);
            return true;
        } else {

            // Si falla, restamos un intento en el modelo del juego
            int intentosRestantes = partidaActual.getIntentos();
            partidaActual.setIntentos(intentosRestantes - 1);
            return false;
        }
    }

    public void jugarDenuevo() {
        List<String> palabras = palabraRepository.getPalabras();
        String nuevaPalabra = palabras.get(new Random().nextInt(palabras.size()));

        partidaActual.setIntentos(3);
        partidaActual.setPalabraSeleccionada(nuevaPalabra);
        partidaActual.setPalabraDesordenada(desordenarPalabra(nuevaPalabra));
    }

    // Getter para la ventana
    public String getPalabra() {
        return partidaActual.getPalabraDesordenada();
    }
    public int getIntentos() {
        return partidaActual.getIntentos();
    }
    public String getPalabraSeleccionada() {
        return partidaActual.getPalabraSeleccionada();
    }
}
