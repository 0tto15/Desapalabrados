package com.iessanalberto.dam1.desapalabrados;

import com.iessanalberto.dam1.desapalabrados.screens.MainScreen;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        MainScreen mainScreen = new MainScreen();
        Scene scene = new Scene(mainScreen.getRoot(), 320, 240);
        stage.setTitle("Desapalabrados");
        stage.setScene(scene);
        stage.show();
    }
}
