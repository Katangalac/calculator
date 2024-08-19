module Calculator {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;

    opens Calculator to javafx.fxml;
    opens Calculator.Gui to javafx.fxml;

    exports Calculator.Gui;
    exports Calculator;
}