package Calculator.Gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MainWindow extends Application {
    public void start(Stage primaryStage) throws IOException {
        FXMLLoader loader = new FXMLLoader(MainWindow.class.getResource("/calculatorFXML/calculator.fxml"));
        Scene scene = new Scene(loader.load(), 320, 240);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Calculator");
        primaryStage.setMaximized(true);
        primaryStage.show();
    }

    public static void main(String[] args) {launch();}

}
