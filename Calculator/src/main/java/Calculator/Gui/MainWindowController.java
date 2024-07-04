package Calculator.Gui;

import Calculator.Domain.CalculatorController.CalculatorController;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

import java.io.IOException;

public class MainWindowController {
    @FXML
    private BorderPane mainContentBorderPane;

    @FXML
    private HBox calculatorMenuButton;

    @FXML
    private HBox equationSolverMenuButton;

    @FXML
    private HBox triangleSolverMenuButton;

    @FXML
    private HBox formulasMenuButton;

    @FXML
    private HBox helpMenuButton;

    @FXML
    private HBox exitButton;

    @FXML
    public void initialize() {
        loadMainContentPane("/gui/calculatorPane.fxml");
        initializeMenuButtonsActions();
    }

    private void initializeMenuButtonsActions(){
        calculatorMenuButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                handleCalculatorMenuButtonAction();
            }
        });

        equationSolverMenuButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                handleEquationSolverMenuButtonAction();
            }
        });

        triangleSolverMenuButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                handleTriangleSolverMenuButtonAction();
            }
        });

        formulasMenuButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                handleformulasMenuButtonAction();
            }
        });

        helpMenuButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                handleHelpMenuButtonAction();
            }
        });

        exitButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Stage stage = (Stage) exitButton.getScene().getWindow();
                stage.close();
            }
        });
    }

    private void loadMainContentPane(String fxmlFile){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
            Pane mainContentPane = loader.load();
            mainContentBorderPane.setCenter(mainContentPane);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void handleCalculatorMenuButtonAction(){
        loadMainContentPane("/gui/calculatorPane.fxml");
    }

    private void handleEquationSolverMenuButtonAction(){
        loadMainContentPane("/gui/equationSolverPane.fxml");
    }

    private void handleTriangleSolverMenuButtonAction(){
        loadMainContentPane("/gui/triangleSolverPane.fxml");
    }

    private void handleformulasMenuButtonAction(){
        loadMainContentPane("/gui/formulasPane.fxml");
    }

    private void handleHelpMenuButtonAction(){
        loadMainContentPane("/gui/helpPane.fxml");
    }

}