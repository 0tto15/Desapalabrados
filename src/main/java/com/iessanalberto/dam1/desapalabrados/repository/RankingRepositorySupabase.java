package com.iessanalberto.dam1.desapalabrados.repository;

import com.iessanalberto.dam1.desapalabrados.connexion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class RankingRepositorySupabase implements  IRankingRepository {
    @Override
    public void guardarPuntuacion(String nombre, int puntos) {
        String sql = "INSERT INTO ranking (nombre_jugador, puntuacion) VALUES (?, ?)";

        try (Connection connection = connexion.ConnectionBasicSupabase.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, nombre);
            preparedStatement.setInt(2, puntos);
            preparedStatement.executeUpdate();

        } catch (Exception e) {
            System.err.println("Error al guardar puntuación: " + e.getMessage());
        }
    }

    @Override
    public List<String> obtenerTopPuntuaciones() {
        List<String> top = new ArrayList<>();
        // Sentencia que ordena de mayor a menor y muestra los 5 primeros.
        String sql = "SELECT nombre_jugador, puntuacion FROM ranking ORDER BY puntuacion DESC LIMIT 5";

        try (Connection connection = connexion.ConnectionBasicSupabase.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            int puesto = 1;
            while (resultSet.next()) {
                String fila = puesto + ". " + resultSet.getString("nombre_jugador") + " - " + resultSet.getInt("puntuacion") + " pts";
                top.add(fila);
                puesto++;
            }

        } catch (Exception e) {
            System.err.println("Error al obtener ranking: " + e.getMessage());
            top.add("No se pudo cargar el ranking");
        }
        return top;
    }
}
