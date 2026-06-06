package com.iessanalberto.dam1.desapalabrados.repository;
import java.util.List;
import java.util.Map;

public interface IRankingRepository {
    void guardarPuntuacion(String nombre, int puntos);
    List<String> obtenerTopPuntuaciones(); // Devuelve una lista formateada para la UI
}
