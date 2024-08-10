package Calculator.Gui;

import Calculator.Domain.CalculatorController.CalculatorController;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.event.ActionEvent;

public class CalculatorPaneController {

    @FXML
    private TextField screen;

    private final CalculatorController calculatorController = new CalculatorController();
    private double previousAnswer;
    private int caretPosition;
    private boolean needClear;

    @FXML
    public void initialize() {
        initializeScreen();
    }

    @FXML
    private void handleButtonAction(ActionEvent event) {
        Button sourceButton = (Button) event.getSource();
        String buttonText = sourceButton.getText();
        String spacing = "";

        boolean needPrint = true;
        //"⌈𝑥⌉" "⌊𝑥⌋"
        switch (buttonText) {
            case "=":
                calculate();
                needPrint = false;
                break;

            case "Clear":
                needPrint = false;
                clear();
                break;

            case "Delete":
                needPrint = false;
                deleteOneCharacter();
                break;

            case "+":
            case "-":
            case "x":
            case "/":
            case "%":
                spacing = " ";
                break;

            case "sin":
            case "cos":
            case "tan":
            case "cotan":
            case "sec":
            case "cosec":
            case "ln":
                buttonText = buttonText + "()";
                break;
            case "log":
                buttonText = "log(,)";
                break;
            case "𝑥!":
                buttonText = "!";
                break;

            case "ceil":
                buttonText = "ceil()";
                break;

            case "floor":
                buttonText = "floor()";
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

            case "Ans":
                buttonText = Double.toString(previousAnswer);
                break;
            default:
                break;
        }

        if(needPrint){
            printOnScreen(buttonText,  spacing);
        }
    }

    private void initializeScreen() {
        needClear = false;
        screen.setText("");
        screen.caretPositionProperty().addListener((obs, oldPos, newPos) -> {
            int caretPosition;
            if(newPos.intValue() > 0){
                caretPosition = newPos.intValue();
                this.caretPosition = caretPosition;
            }
        });
    }



    private void printOnScreen(String text, String spacing) {
        if(needClear){
            clear();
        }
        int pos = this.caretPosition;
        String currentText = screen.getText();
        String upgradedText = spacing + text + spacing;
        String newText = currentText.substring(0,pos) + upgradedText + currentText.substring(pos);
        screen.setText(newText);
        screen.positionCaret(pos + upgradedText.length());
    }

    private void clear(){
        needClear = false;
        screen.setText("");
        this.caretPosition = 0;
    }

    private void deleteOneCharacter(){
        screen.setText(screen.getText().substring(0, screen.getText().length()-1));
    }


    private void calculate(){
        String expression = screen.getText();
        if(expression.isEmpty()){
            previousAnswer = 0;
        }else{
            previousAnswer = calculatorController.calculate(expression);
        }
        String result = String.valueOf(previousAnswer);
        screen.setText(removeDecimalPart(result));
        needClear = true;
    }

    private String removeDecimalPart(String result){
        String[] parts = result.split("\\.");
        if(parts.length == 2){
            if(parts[1].equals("0")){
                return parts[0];
            }
        }
        return result;
    }


}
