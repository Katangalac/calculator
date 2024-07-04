package Calculator.Gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MainWindow extends Application {
    public void start(Stage primaryStage) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/mainWindow.fxml"));
        Scene scene = new Scene(loader.load(), 320, 240);
        scene.getStylesheets().add(getClass().getResource("/gui/css/styles.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.setTitle("Calculator");
        primaryStage.setMaximized(true);
        primaryStage.show();
    }

    public static void main(String[] args) {launch();}

}
