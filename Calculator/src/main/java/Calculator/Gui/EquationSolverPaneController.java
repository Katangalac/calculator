package Calculator.Gui;

import Calculator.Domain.CalculatorController.CalculatorController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.geometry.Insets;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class EquationSolverPaneController {

    @FXML
    private Spinner<String> equationTypeSpinner;

    @FXML
    private VBox equationSolverVBox;

    @FXML
    private Text equationFormText;

    private final CalculatorController calculatorController = new CalculatorController();
    private Map<String, String> equationForms;
    private Map<String, Integer> equationCoefficients;
    private Map<String, Integer> equationNumber;
    private Map<String, Integer> equationVariables;
    private Map<String, Boolean> isSystem;

    @FXML
    public void initialize() {
        initializeEquationFormMap();
        initializeEquationCoefficients();
        initializeEquationTypeSpinnerOptions();
    }


    private void initializeEquationFormMap(){
        equationForms = new HashMap<>();
        equationForms.put("Linear Equation", "Ax + B = 0");
        equationForms.put("Quadratic Equation", "Ax² + Bx + C = 0");
        equationForms.put("system of 2 equations with 2 unknowns", "\n" + "  A₁ x  +  B₁ y = C₁\n" +
                "  A₂ x  +  B₂ y = C₂\n");
        equationForms.put("system of 3 equations with 3 unknowns", "\n" +
                "  A₁ x  +  B₁ y  +  C₁ z = D₁\n" +
                "  A₂ x  +  B₂ y  +  C₂ z = D₂\n" +
                "  A₃ x  +  B₃ y  +  C₃ z = D₃\n");
    }

    private void initializeEquationCoefficients(){
        equationCoefficients = new HashMap<>();
        equationNumber = new HashMap<>();
        equationVariables = new HashMap<>();
        isSystem = new HashMap<>();

        equationCoefficients.put("Linear Equation", 2);
        equationCoefficients.put("Quadratic Equation", 3);
        equationCoefficients.put("system of 2 equations with 2 unknowns", 6);
        equationCoefficients.put("system of 3 equations with 3 unknowns", 12);


        equationNumber.put("Linear Equation", 1);
        equationNumber.put("Quadratic Equation", 1);
        equationNumber.put("system of 2 equations with 2 unknowns", 2);
        equationNumber.put("system of 3 equations with 3 unknowns", 3);

        equationVariables.put("Linear Equation", 2);
        equationVariables.put("Quadratic Equation", 3);
        equationVariables.put("system of 2 equations with 2 unknowns", 3);
        equationVariables.put("system of 3 equations with 3 unknowns", 4);

        isSystem.put("Linear Equation", false);
        isSystem.put("Quadratic Equation", false);
        isSystem.put("system of 2 equations with 2 unknowns", true);
        isSystem.put("system of 3 equations with 3 unknowns", true);
    }

    private void initializeEquationTypeSpinnerOptions() {
        ObservableList<String> options = FXCollections.observableArrayList(
                "system of 3 equations with 3 unknowns", "system of 2 equations with 2 unknowns", "Quadratic Equation", "Linear Equation"

        );
        SpinnerValueFactory<String> valueFactory = new SpinnerValueFactory.ListSpinnerValueFactory<>(options);
        valueFactory.setValue("Linear Equation");
        updateForm("Linear Equation");
        equationFormText.setText("Ax + B = 0");
        equationTypeSpinner.setValueFactory(valueFactory);
        equationTypeSpinner.valueProperty().addListener((observable, oldValue, newValue) -> {
            setEquationFormText(newValue);
            updateForm(newValue);
        });
    }

    private void setEquationFormText(String equationType){
        equationFormText.setText(equationForms.get(equationType));
    }


    private Label[] generateLabelsForEquationType(String equationType, int coefficients) {
        Label[] labels = new Label[coefficients + 1];

        switch (equationType) {
            case "Linear Equation":
                labels[0] = new Label("x +");
                labels[1] = new Label("= 0");
                break;
            case "Quadratic Equation":
                labels[0] = new Label("x² +");
                labels[1] = new Label("x +");
                labels[2] = new Label("= 0");
                break;
            case "system of 2 equations with 2 unknowns":
                labels[0] = new Label("x +");
                labels[1] = new Label("y =");
                labels[2] = new Label("x +");
                labels[3] = new Label("y =");
                break;
            case "system of 3 equations with 3 unknowns":
                labels[0] = new Label("x +");
                labels[1] = new Label("y +");
                labels[2] = new Label("z =");
                labels[3] = new Label("x +");
                labels[4] = new Label("y +");
                labels[5] = new Label("z =");
                labels[6] = new Label("x +");
                labels[7] = new Label("y +");
                labels[8] = new Label("z =");
                break;
            default:
                break;
        }

        for (Label label : labels) {
            if (label != null) {
                label.getStyleClass().add("equation-label");
            }
        }

        return labels;
    }


    private List<Node> createSystemsEquationsNodeArray(List<TextField> textFields, Label[] labels, int numberOfEquations, int variables, Boolean system) {
        List<Node> nodes = new ArrayList<>();
        List<HBox> hBoxes = new ArrayList<>();
        int index = 0;
        int labelIndex = 0;

        for (int i = 0; i < numberOfEquations; i++) {
            HBox hbox = new HBox();
            hbox.setPadding(new Insets(0, 0, 0, 10));
            hbox.getStyleClass().add("equation-solver-inputHbox");
            hBoxes.add(hbox);
        }

        for (int i = 0; i < numberOfEquations; i++) {
            for (int j = 0; j < variables; j++) {
                HBox hbox = hBoxes.get(i);
                hbox.getChildren().add(textFields.get(index));
                if(system && j == variables - 1) {
                    index += 1;
                    continue;
                }
                if(labels[labelIndex] != null) {
                    hbox.getChildren().add(labels[labelIndex]);
                }
                index += 1;
                labelIndex += 1;
            }
            nodes.add(hBoxes.get(i));
        }

        return nodes;
    }

    private void updateForm(String equationType) {
        Boolean system = isSystem.get(equationType);
        int numberOfequation = equationNumber.get(equationType);
        int variables = equationVariables.get(equationType);
        equationSolverVBox.getChildren().clear();

        int coefficients = equationCoefficients.get(equationType);
        List<TextField> textFields = new ArrayList<>();

        for (int i = 0; i < coefficients; i++) {
            TextField textField = new TextField();
            textField.getStyleClass().add("coefficient-field");
            textFields.add(textField);
        }

        HBox newHbox = new HBox();
        newHbox.setPadding(new Insets(20, 0, 0, 0));
        newHbox.getStyleClass().add("equation-solution-hbox");
        Button solveButton = new Button("Solve");
        solveButton.setOnAction(actionEvent -> solve(equationType, textFields));
        Label[] labels = generateLabelsForEquationType(equationType, coefficients);
        List<Node> nodes = createSystemsEquationsNodeArray(textFields, labels, numberOfequation, variables, system);
        nodes.add(solveButton);
        equationSolverVBox.getChildren().addAll(nodes);
        equationSolverVBox.getChildren().add(newHbox);
    }


    private void solve(String type, List<TextField> textFields){
        int lastIndex = equationSolverVBox.getChildren().size() - 1;
        HBox solutionBox = (HBox) equationSolverVBox.getChildren().get(lastIndex);
        solutionBox.getChildren().clear();

        if(checkTextFieldValue(textFields)){
            HBox hbox = createGenericSolutionHbox();
            hbox.setStyle("-fx-background-color: #6db502;");
            hbox.setPadding(new Insets(5, 0, 5, 5));
            HBox solutionHbox1 = createGenericSolutionHbox();
            HBox solutionHbox2 = createGenericSolutionHbox();
            VBox vbox = new VBox();
            vbox.getStyleClass().add("equation-solution-vbox");
            vbox.setPadding(new Insets(0, 0, 10, 0));
            Label title = new Label("Solution");
            title.getStyleClass().add("equation-solution-title-label");
            Label solutionLabel = new Label();
            solutionLabel.getStyleClass().add("equation-solution-label");
            solutionLabel.setStyle("-fx-font-weight: normal");
            Label solutionSet = new Label();
            solutionSet.getStyleClass().add("equation-solution-label");
            List<Double> textfieldValues = new ArrayList<>(textFields.size());
            for(TextField textField : textFields){
                textfieldValues.add(Double.parseDouble(textField.getText()));
            }

            try{
                List<Double> solution = calculatorController.solveEquation(type, textfieldValues);
                createSolutionNodeArray(type, solutionLabel, solutionSet, solution);
                hbox.getChildren().addAll(title);
                solutionHbox1.getChildren().add(solutionLabel);
                solutionHbox2.getChildren().add(solutionSet);
                vbox.getChildren().addAll(hbox, solutionHbox1, solutionHbox2);
                solutionBox.getChildren().addAll(vbox);
            }catch (Exception e){
                Label label = new Label(e.getMessage());
                label.getStyleClass().add("equation-solution-label");
                label.setStyle("-fx-text-fill: #d50202;");
                solutionBox.getChildren().add(label);
            }
        }else{
            Label label = new Label("All empty fields need to be filled with number to solve the equation!");
            label.getStyleClass().add("equation-solution-label");
            label.setStyle("-fx-text-fill: #d50202;");
            solutionBox.getChildren().add(label);
        }

    }

    private void createSolutionNodeArray(String type, Label label, Label s, List<Double> solution) {
        switch (type) {
            case "Linear Equation":
                label.setText("x = " + solution.get(0));
                s.setText("S = { " + solution.get(0) + " }");
                break;

            case "Quadratic Equation":
                label.setText("x = " + solution.get(0));
                s.setText("S = { " + solution.get(0) + " }");
                if(solution.size() > 1){
                    label.setText("x = " + solution.get(0) + "   or    x = " + solution.get(1));
                    s.setText("S = { " + solution.get(0) + ", " + solution.get(1) + " }");
                }
                break;

            case "system of 2 equations with 2 unknowns":
                label.setText("x = " + solution.get(0) + "    and    y = " + solution.get(1));
                s.setText("S = { " + "( " + solution.get(0) + ", " + solution.get(1) + " )" + " }");
                break;

            case "system of 3 equations with 3 unknowns":
                label.setText("x = " + solution.get(0) + " ,    " + "y = " + solution.get(1) + "  and    " +
                        "z = " + solution.get(2));
                s.setText("S = { " + "( " + solution.get(0) + ", " + solution.get(1) + ", " + solution.get(2) + " )" + " }");
                break;
        }
    }

    private boolean checkTextFieldValue(List<TextField> textFields){
        boolean isValid = true;
        for(TextField textField : textFields){
            if(textField.getText().isEmpty() || !containsOnlyDigits(textField.getText())){
                isValid = false;
                break;
            }
        }
        return isValid;
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

    private HBox createGenericSolutionHbox(){
        HBox hbox = new HBox();
        hbox.getStyleClass().add("equation-solution-hbox");
        hbox.setPadding(new Insets(0, 0, 0, 10));
        return hbox;
    }

}
