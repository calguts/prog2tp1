module com.example.prog2tp1 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.prog2tp1 to javafx.fxml;
    exports com.example.prog2tp1;
}