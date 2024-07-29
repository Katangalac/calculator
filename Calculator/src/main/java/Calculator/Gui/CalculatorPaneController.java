package Calculator.Gui;

import Calculator.Domain.CalculatorController.CalculatorController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.event.ActionEvent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CalculatorPaneController {

    @FXML
    private TextField screen;

    @FXML
    private RadioButton unitConvertButton;

    @FXML
    private RadioButton numberConvertButton;

    @FXML
    private RadioButton convertOffButton;

    @FXML
    private Spinner<String> inputTypeSpinner;

    @FXML
    private Spinner<String> outputTypeSpinner;

    private CalculatorController calculatorController = new CalculatorController();
    private List<String> unitList = new ArrayList<>();
    private List<String> numberList = new ArrayList<>();
    private double previousAnswer;

    @FXML
    public void initialize() {
        initializeScreen();
        initializeSpinnerList();
        initializeConvertButton();
    }

    @FXML
    private void handleButtonAction(ActionEvent event) {
        Button sourceButton = (Button) event.getSource();
        String buttonText = sourceButton.getText();
        String spacing = "";

        Boolean needPrint = true;

        switch (buttonText) {
            case "=":
                calculate();
                needPrint = false;
                break;

            case "Clear":
                needPrint = false;
                clear();
                break;

            case "Del":
                needPrint = false;
                deleteOneCharacter();
                break;

            case "+":
            case "-":
            case "x":
            case "/":
            case "mod":
                spacing = " ";
                break;

            case "sin":
            case "cos":
            case "tan":
            case "cotan":
            case "sec":
            case "cosec":
            case "log":
            case "ln":
                buttonText = buttonText + "()";
                break;

            case "𝑥!":
                buttonText = "!";
                break;

            case "⌈𝑥⌉":
                buttonText = "⌈()⌉";
                break;

            case "⌊𝑥⌋":
                buttonText = "⌊()⌋";
                break;

            case "| 𝑥 |":
                buttonText = "|()|";
                break;
            case "√𝑥":
                buttonText = "√()";
                break;

            case "𝑥²":
                buttonText = "²";
                break;

            case "( - )":
                buttonText = "-";
                break;

            default:
                break;
        }

        if(needPrint){
            printOnScreen(buttonText,  spacing);
        }
    }

    private void initializeInputSpinner() {
        List<String> optionsList = new ArrayList<>();
        if(unitConvertButton.isSelected()) {
            optionsList = unitList;
            inputTypeSpinner.setDisable(false);
        } else if (numberConvertButton.isSelected()) {
            optionsList = numberList;
            inputTypeSpinner.setDisable(false);
        }else if (convertOffButton.isSelected()) {
            optionsList = List.of("None");
            inputTypeSpinner.setDisable(true);
        }

        ObservableList<String> options = FXCollections.observableArrayList(
                optionsList
        );
        SpinnerValueFactory<String> valueFactory = new SpinnerValueFactory.ListSpinnerValueFactory<>(options);
        valueFactory.setValue(optionsList.get(optionsList.size() - 1));
        inputTypeSpinner.setValueFactory(valueFactory);
        inputTypeSpinner.valueProperty().addListener((observable, oldValue, newValue) -> {
        });
    }

    private void initializeOutputSpinner() {
        List<String> optionsList = new ArrayList<>();
        if(unitConvertButton.isSelected()) {
            optionsList = unitList;
            outputTypeSpinner.setDisable(false);
        } else if (numberConvertButton.isSelected()) {
            optionsList = numberList;
            outputTypeSpinner.setDisable(false);
        }else if (convertOffButton.isSelected()) {
            optionsList = List.of("None");
            outputTypeSpinner.setDisable(true);
        }

        ObservableList<String> options = FXCollections.observableArrayList(
                optionsList
        );
        SpinnerValueFactory<String> valueFactory = new SpinnerValueFactory.ListSpinnerValueFactory<>(options);
        valueFactory.setValue(optionsList.get(optionsList.size() - 1));
        outputTypeSpinner.setValueFactory(valueFactory);
        outputTypeSpinner.valueProperty().addListener((observable, oldValue, newValue) -> {
        });
    }

    private void initializeScreen() {
        screen.setText("");
    }

    private void initializeConvertButton() {
        unitConvertButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                convertButtonsActions(true, false, false);
            }
        });

        numberConvertButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                convertButtonsActions(false, true, false);
            }
        });

        convertOffButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                convertButtonsActions(false, false, true);
            }
        });

        convertButtonsActions(false, false, true);
    }

    private void initializeSpinnerList(){
        unitList.add("Gradians");
        unitList.add("Radians");
        unitList.add("Degrees");

        numberList.add("Hexadecimal");
        numberList.add("Binary");
        numberList.add("Decimal");
    }

    private void printOnScreen(String text, String spacing) {
        screen.setText(screen.getText() + spacing + text + spacing);
    }

    private void clear(){
        screen.setText("");
    }

    private void deleteOneCharacter(){
        screen.setText(screen.getText().substring(0, screen.getText().length()-1));
    }

    private void convertButtonsActions(Boolean unitConvertState, Boolean numberConvertState, Boolean convertOffState){
        unitConvertButton.setSelected(unitConvertState);
        numberConvertButton.setSelected(numberConvertState);
        convertOffButton.setSelected(convertOffState);
        initializeInputSpinner();
        initializeOutputSpinner();
    }

    private void calculate(){
        String expression = screen.getText();
        if(expression.equals("")){
            previousAnswer = 0;
        }else{
            previousAnswer = calculatorController.calculate(expression);
        }
        screen.setText(String.valueOf(previousAnswer));
    }
}
