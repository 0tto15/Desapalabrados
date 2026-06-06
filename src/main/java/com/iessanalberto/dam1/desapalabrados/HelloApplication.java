package com.iessanalberto.dam1.desapalabrados;

import com.iessanalberto.dam1.desapalabrados.repository.*;
import com.iessanalberto.dam1.desapalabrados.screens.MainScreen;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

import com.iessanalberto.dam1.desapalabrados.services.PalabraService;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {

        // 1. Creamos el almacén de datos (Capa de Datos)
        IPalabraRepository palabraRepository = new PalabraRepositorySubabase();
        IRankingRepository rankingRepository = new RankingRepositorySupabase();

        // 2. Creamos el cerebro pasándole el repositorio (Capa de Negocio)
        PalabraService palabraService = new PalabraService(palabraRepository,rankingRepository);

        // 3. Creamos la pantalla pasándole el servicio (Capa de Vista)
        MainScreen mainScreen = new MainScreen(palabraService);

        // 4. Cargamos la pantalla en la escena de JavaFX
        Scene scene = new Scene(mainScreen.getRoot(), 320, 240);
        stage.setTitle("Desapalabrados");
        stage.setScene(scene);
        stage.show();
    }
    public static void main(String[] args) {
        launch();
    }
}
