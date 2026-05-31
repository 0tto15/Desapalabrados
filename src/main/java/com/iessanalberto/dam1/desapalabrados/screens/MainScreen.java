package com.iessanalberto.dam1.desapalabrados.screens;

import com.iessanalberto.dam1.desapalabrados.repository.PalabraRepository;
import com.iessanalberto.dam1.desapalabrados.services.PalabraService;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;

/**
 * Author: Daniel C.A
 * Dathe: 31/05/26
 * Version: 1.0
 * Description: Primera versión de Desapalabrado.
 * Se pueden mejorar demasíadas cosas. ¿Clase Palabra/Jugador? sus propiedades podrían ser los intentos
 * array de palabras y la palabra seleccionada, esas cosas
 * las estoy declarando en el propio services y repository.
 *
 * Estoy abusando del setDisable de los botones, quizá podría jugar con un bucle while y la propiedad intentos.
 */


public class MainScreen {
    //Elementos del layout
    private GridPane root;

    //Componentes de ventana
    private Label lblUsername = new Label("Adivina la palabra:");
    private TextField txtPalabraSecreta = new TextField();
    private Label lblPassword = new Label("Ingresa tu respuesta: ");
    private TextField txtPalabra = new TextField();
    private Button btnComprobar = new Button("Comprobar");
    private Label lblMensaje = new Label("Número de intentos: ");
    private TextField txtIntentos = new TextField("3");
    private Button btnVolverJugar = new  Button("Jugar de nuevo");

    // Servicios de ventana
    private PalabraRepository palabraRepository = new PalabraRepository();
    private PalabraService palabraServices = new PalabraService();

    public MainScreen() {
        //Edición de ventana
        this.root = new GridPane(); //declarar el GridPane para que funcione.
        root.add(lblUsername,0,0);
        root.add(txtPalabraSecreta,1,0);
        root.add(lblPassword,0,1);
        root.add(txtPalabra,1,1);
        root.add(btnComprobar,0,5);
        root.add(txtIntentos,1,3);
        root.add(lblMensaje,0,3);
        root.add(btnVolverJugar,1,5);


        // Protección contra modificaciones
        txtPalabraSecreta.setEditable(false);
        txtIntentos.setEditable(false);
        btnVolverJugar.setDisable(true);
        txtPalabra.setPromptText("Ingresa una respuesta");

        // Modificaciones
        try {
            txtPalabraSecreta.setText(palabraServices.getPalabra());
        } catch (Exception exception) {
            throw new RuntimeException(exception);
        }
        txtIntentos.setText(String.valueOf(palabraServices.getIntentos()));


        // Interactividad
        btnComprobar.setOnAction(e -> {
            try {
                boolean intento = palabraServices.comprobarPalabra(txtPalabra.getText());
                    if (intento) {

                        try {
                            txtPalabraSecreta.setText(palabraServices.getPalabra());
                        } catch (Exception exception) {
                            throw new RuntimeException(exception);
                        }

                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("¡Felicidades!");
                        alert.setHeaderText(null);
                        alert.setContentText("Has acertado correctamente.");
                        alert.showAndWait();

                        btnComprobar.setDisable(true);
                        btnVolverJugar.setDisable(false);

                    } else  {
                        txtIntentos.setText(String.valueOf(palabraServices.getIntentos()));
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("¡Palabra equivocada!");
                        alert.setHeaderText(null);
                        alert.setContentText("Esa no es la palabra, te quedan "+txtIntentos.getText()+" intentos.");
                        alert.showAndWait();

                        if (palabraServices.getIntentos()==0) {
                            Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
                            alert1.setTitle("¡Lo sentimos!");
                            alert1.setHeaderText(null);
                            alert1.setContentText("Ya no te quedan intentos, la palabra correcta era: "+ palabraRepository.getPalabraSeleccionada());
                            alert1.showAndWait();

                            btnComprobar.setDisable(true);
                            txtPalabra.clear();
                            btnVolverJugar.setDisable(false);
                        }
                    }

            } catch (Exception exception) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("¡Lo sentimos!");
                alert.setHeaderText(null);
                alert.setContentText(exception.getMessage());
                alert.showAndWait();
            }
        });
        btnVolverJugar.setOnAction(e -> {
            palabraServices.jugarDenuevo();
            txtIntentos.setText(String.valueOf(palabraServices.getIntentos()));
            try {
                txtPalabraSecreta.setText(palabraServices.getPalabra());

                btnVolverJugar.setDisable(true);
                btnComprobar.setDisable(false);
            } catch (Exception exception) {
                throw new RuntimeException(exception);
            }
        });
    }
    // Getter
    public GridPane getRoot() {
        return root;
    }
}
