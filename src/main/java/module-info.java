module com.iessanalberto.dam1.desapalabrados {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;


    opens com.iessanalberto.dam1.desapalabrados to javafx.fxml;
    exports com.iessanalberto.dam1.desapalabrados;
}