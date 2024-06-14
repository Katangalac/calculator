package Calculator;

import Calculator.Gui.MainWindow;

import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    public void start(Stage primaryStage) throws IOException {
        MainWindow mainWindow = new MainWindow();
        mainWindow.start(primaryStage);
    }
    public static void main(String[] args) {
        launch();
    }
}