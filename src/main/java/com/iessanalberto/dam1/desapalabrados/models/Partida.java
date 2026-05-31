package com.iessanalberto.dam1.desapalabrados.models;

import java.util.List;

public class Partida {
    private Jugador jugador;
    private int intentos = 3;
    private String palabraSeleccionada;
    private String palabraDesordenada;
    private List<String> listaPalabras;

    public Partida(Jugador jugador, int intentos, String palabraSeleccionada, String desordenada, List<String> listaPalabras) {
        this.jugador = jugador;
        this.intentos = intentos;
        this.palabraSeleccionada = palabraSeleccionada;
        this.palabraDesordenada = desordenada;
        this.listaPalabras = listaPalabras;
    }
    // Getters
    public int getIntentos() {
        return intentos;
    }
    public String getPalabraSeleccionada() {
        return palabraSeleccionada;
    }
    public Jugador getJugador() {
        return jugador;
    }
    public String getPalabraDesordenada() {
        return palabraDesordenada;
    }

    // Setter
    public void setIntentos(int intentos) {
        this.intentos = intentos;
    }
    public void setPalabraSeleccionada(String palabraSeleccionada) {
        this.palabraSeleccionada = palabraSeleccionada;
    }
    public void setPalabraDesordenada(String palabraDesordenada) {
        this.palabraDesordenada = palabraDesordenada;
    }
}
