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
 */
public class MainScreen {
    // Elementos del layout
    private GridPane root;

    // Componentes de ventana
    private Label lblUsername = new Label("Adivina la palabra:");
    private TextField txtPalabraSecreta = new TextField();
    private Label lblRespuesta = new Label("Ingresa tu respuesta: ");
    private TextField txtPalabra = new TextField();
    private Button btnComprobar = new Button("Comprobar");
    private Label lblMensaje = new Label("Número de intentos: ");
    private TextField txtIntentos = new TextField();
    private Button btnVolverJugar = new Button("Jugar de nuevo");

    private PalabraService palabraService;

    public MainScreen(PalabraService palabraService) {
        this.palabraService = palabraService;
        this.root = new GridPane();

        inicializarUI();
        configurarEventos();
        actualizarDatosPantalla();
    }

    // 1. Método para agrupar todo lo visual
    private void inicializarUI() {
        root.add(lblUsername, 0, 0);
        root.add(txtPalabraSecreta, 1, 0);
        root.add(lblRespuesta, 0, 1);
        root.add(txtPalabra, 1, 1);
        root.add(txtIntentos, 1, 3);
        root.add(lblMensaje, 0, 3);
        root.add(btnComprobar, 0, 5);
        root.add(btnVolverJugar, 1, 5);

        // Protección contra modificaciones
        txtPalabraSecreta.setEditable(false);
        txtIntentos.setEditable(false);
        btnVolverJugar.setDisable(true);
        txtPalabra.setPromptText("Ingresa una respuesta");
    }

    // 2. Método para actualizar los textos desde el servicio
    private void actualizarDatosPantalla() {
        txtPalabraSecreta.setText(palabraService.getPalabra());
        txtIntentos.setText(String.valueOf(palabraService.getIntentos()));
    }

    // 3. Método para gestionar los clics de los botones
    private void configurarEventos() {
        btnComprobar.setOnAction(e -> comprobarIntento());

        btnVolverJugar.setOnAction(e -> {
            palabraService.jugarDenuevo();
            actualizarDatosPantalla();
            btnVolverJugar.setDisable(true);
            btnComprobar.setDisable(false);
            txtPalabra.clear(); // Limpiamos el texto escrito por el usuario
        });
    }

    // 4. Lógica de comprobación extraída para mayor claridad
    private void comprobarIntento() {
        try {
            boolean acierto = palabraService.comprobarPalabra(txtPalabra.getText());

            if (acierto) {
                actualizarDatosPantalla();
                mostrarAlerta(Alert.AlertType.INFORMATION, "¡Felicidades!", "Has acertado correctamente.");
                btnComprobar.setDisable(true);
                btnVolverJugar.setDisable(false);
            } else {
                txtIntentos.setText(String.valueOf(palabraService.getIntentos()));
                mostrarAlerta(Alert.AlertType.INFORMATION, "¡Palabra equivocada!",
                        "Esa no es la palabra, te quedan " + txtIntentos.getText() + " intentos.");

                if (palabraService.getIntentos() == 0) {

                    String palabraCorrecta = palabraService.getPalabraSeleccionada();
                    mostrarAlerta(Alert.AlertType.INFORMATION, "¡Lo sentimos!",
                            "Ya no te quedan intentos, la palabra correcta era: " + palabraCorrecta);

                    btnComprobar.setDisable(true);
                    txtPalabra.clear();
                    btnVolverJugar.setDisable(false);
                }
            }
        } catch (Exception exception) {
            mostrarAlerta(Alert.AlertType.WARNING, "¡Lo sentimos!", exception.getMessage());
        }
    }

    // 5. Método auxiliar para no repetir código creando Alerts
    private void mostrarAlerta(Alert.AlertType tipo, String titulo, String mensaje) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
    // Getter
    public GridPane getRoot() {
        return root;
    }
}
