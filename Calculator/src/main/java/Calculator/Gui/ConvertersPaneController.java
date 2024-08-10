package Calculator.Gui;

import Calculator.Domain.CalculatorController.CalculatorController;
import Calculator.Domain.CalculatorModelisation.Converters.NumberConversionType;
import Calculator.Domain.CalculatorModelisation.Converters.UnitConversionType;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

import java.util.*;

public class ConvertersPaneController {
    @FXML
    private RadioButton unitConvertButton;

    @FXML
    private RadioButton numberConvertButton;

    @FXML
    private Spinner<String> inputTypeSpinner;

    @FXML
    private Spinner<String> outputTypeSpinner;

    @FXML
    private TextField inputField;

    @FXML
    private TextField outputField;

    @FXML
    private Label remarkLabel;

    @FXML
    public void initialize() {
        initializeSpinnerList();
        initializeConvertButton();
    }

    private final CalculatorController calculatorController = new CalculatorController();
    private final List<String> unitList = new ArrayList<>();
    private final List<String> numberList = new ArrayList<>();

    private void initializeSpinnerList(){
        unitList.add("Gradians");
        unitList.add("Radians");
        unitList.add("Degrees");

        numberList.add("Hexadecimal");
        numberList.add("Binary");
        numberList.add("Decimal");
    }

    private void initializeInputSpinner() {
        List<String> optionsList = new ArrayList<>();
        if(unitConvertButton.isSelected()) {
            optionsList = unitList;
            inputTypeSpinner.setDisable(false);
        } else if (numberConvertButton.isSelected()) {
            optionsList = numberList;
            inputTypeSpinner.setDisable(false);
        }

        ObservableList<String> options = FXCollections.observableArrayList(
                optionsList
        );
        SpinnerValueFactory<String> valueFactory = new SpinnerValueFactory.ListSpinnerValueFactory<>(options);
        valueFactory.setValue(optionsList.get(optionsList.size() - 1));
        inputTypeSpinner.setValueFactory(valueFactory);
        inputTypeSpinner.valueProperty().addListener((observable, oldValue, newValue) -> {
            clearFields();
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
        }
        ObservableList<String> options = FXCollections.observableArrayList(
                optionsList
        );
        SpinnerValueFactory<String> valueFactory = new SpinnerValueFactory.ListSpinnerValueFactory<>(options);
        valueFactory.setValue(optionsList.get(optionsList.size() - 1));
        outputTypeSpinner.setValueFactory(valueFactory);
        outputTypeSpinner.valueProperty().addListener((observable, oldValue, newValue) -> {
            clearFields();
        });
    }

    private void initializeConvertButton() {
        unitConvertButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                convertButtonsActions(true, false);
            }
        });

        numberConvertButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                convertButtonsActions(false, true);
            }
        });
        convertButtonsActions(true, false);
    }

    private void convertButtonsActions(Boolean unitConvertState, Boolean numberConvertState){
        unitConvertButton.setSelected(unitConvertState);
        numberConvertButton.setSelected(numberConvertState);
        initializeInputSpinner();
        initializeOutputSpinner();
    }

    private void checkSpinners(){
        if(inputTypeSpinner.getValue().equals(outputTypeSpinner.getValue())){
            throw new IllegalArgumentException("Input type and output type must be different!");
        }
    }

    private UnitConversionType findUnitConversionType(){
        String type = inputTypeSpinner.getValue() + outputTypeSpinner.getValue();
        return UnitConversionType.fromString(type);
    }

    private NumberConversionType findNumberConversionType(){
        String type = inputTypeSpinner.getValue() + outputTypeSpinner.getValue();
        return NumberConversionType.fromString(type);
    }

    private void checkUnitConvertInput(String input){
        if(!containsOnlyDigits(input)){
            throw new IllegalArgumentException("Input must be digits");
        }
    }

    private void checkNumberConvertInput(String input){
        if(inputTypeSpinner.getValue().equals("Decimal")){
            if(!isInt(input)){
                throw new IllegalArgumentException("Input must be a positive integer!");
            }
        }else if(inputTypeSpinner.getValue().equals("Hexadecimal")){
            if(!checkHexadecimalInput(input)){
                throw new IllegalArgumentException("Input must be digits or characters among A,B,C,D,E,F!");
            }
        }else if(inputTypeSpinner.getValue().equals("Binary")){
            if(!isBinary(input)){
                throw new IllegalArgumentException("Input must be digits among 0 and 1!");
            }
        }
    }

    @FXML
    private void convert(){
        remarkLabel.setText("");
        try{
            checkEmptyField();
            String value = inputField.getText();
            if(unitConvertButton.isSelected()){
                checkSpinners();
                checkUnitConvertInput(value);
                double convertedValue = calculatorController.convertUnit(findUnitConversionType(), Double.parseDouble(value));
                outputField.setText(Double.toString(convertedValue));
            }
            if(numberConvertButton.isSelected()){
                checkNumberConvertInput(value);
                if(inputTypeSpinner.getValue().equals("Decimal")){
                    String convertedValue = calculatorController.convertDecimal(findNumberConversionType(), Integer.parseInt(value));
                    outputField.setText(convertedValue);
                }else if(outputTypeSpinner.getValue().equals("Decimal")){
                    int convertedValue = calculatorController.convertToDecimal(findNumberConversionType(), value);
                    outputField.setText(Integer.toString(convertedValue));
                }else{
                    String convertedValue = calculatorController.convertNonDecimalNumber(findNumberConversionType(), value);
                    outputField.setText(convertedValue);
                }
            }
        }catch (Exception e){
            remarkLabel.setText(e.getMessage());
        }

    }

    private boolean containsOnlyDigits(String value){
        int dotNumber = 0;
        for (int i = 0; i < value.length(); i++) {
            if(value.charAt(i) == '.'){
                if(dotNumber > 0 || value.length() <= 1){
                    return false;
                }
                dotNumber++;
            }

            if(value.charAt(i) == '-'){
                if(value.length() <= 1){
                    return false;
                }
            }

            if (!Character.isDigit(value.charAt(i)) && !(value.charAt(i) == '.') && !(value.charAt(i) == '-')) {
                return false;
            }
        }
        return true;
    }

    private boolean checkHexadecimalInput(String value){
        List<Character> acceptedAlpha = new ArrayList<>(Arrays.asList('a', 'b', 'c', 'd', 'e', 'f', 'A',
                                                                                            'B', 'C', 'D', 'E', 'F'));
        for (int i = 0; i < value.length(); i++) {
            if (!Character.isDigit(value.charAt(i)) && !(acceptedAlpha.contains(value.charAt(i)))) {
                return false;
            }
        }
        return true;
    }

    private boolean isInt(String value){
        for (int i = 0; i < value.length(); i++) {
            if (!Character.isDigit(value.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    private boolean isBinary(String value){
        for (int i = 0; i < value.length(); i++) {
            if (value.charAt(i) == '1' || value.charAt(i) == '0') {
                return false;
            }
        }
        return true;
    }

    private void checkEmptyField(){
        if(inputField.getText().isEmpty()){
            throw new IllegalArgumentException("You must fill the input field (the left field)!");
        }
    }

    private void  clearFields(){
        inputField.clear();
        outputField.clear();
    }

}
