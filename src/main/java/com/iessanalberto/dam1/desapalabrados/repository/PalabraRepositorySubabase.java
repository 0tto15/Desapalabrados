package com.iessanalberto.dam1.desapalabrados.repository;

import com.iessanalberto.dam1.desapalabrados.connexion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PalabraRepositorySubabase implements IPalabraRepository{
    @Override
    public List<String> obtenerTodasLasPalabras() {
        List<String> listaPalabras = new ArrayList<>();
        String sql = "SELECT palabra FROM palabras";

        try (Connection connection = connexion.ConnectionBasicSupabase.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                String palabra = resultSet.getString("palabra");
                listaPalabras.add(palabra);
            }
        } catch (Exception exception) {
            throw new RuntimeException(exception);
        }


        return listaPalabras;
    }
}
