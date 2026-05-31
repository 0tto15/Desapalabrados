package com.iessanalberto.dam1.desapalabrados.models;

import java.util.ArrayList;

public class Jugador {
    private String nickname;
    private int puntuacion;

    // Constructor
    public Jugador(String nickname, int puntuacion) {
        this.nickname = nickname;
        this.puntuacion = puntuacion;
    }
    // Getter
    public String getNickname() {
        return nickname;
    }
    public int getPuntuacion() {
        return puntuacion;
    }
    // Setter (aumentar puntuación)
    public void setPuntuacion(int puntuacion) {
        this.puntuacion = puntuacion;
    }
}
