package Calculator.Gui;

import Calculator.Domain.CalculatorController.CalculatorController;
import Calculator.Domain.CalculatorModelisation.TriangleSolver.TriangleType;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.geometry.Insets;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TriangleSolverPaneController {
    @FXML
    private VBox triangleSolverInputVbox;

    @FXML
    private Spinner<String> triangleTypeSpinner;

    @FXML
    private ImageView triangleImageView;

    @FXML
    private Button solveButton;

    @FXML
    private HBox remarkHbox;

    @FXML
    private HBox solutionBox;

    @FXML
    private Label instructionLabel;

    private final CalculatorController calculatorController = new CalculatorController();
    private int inputNumber;
    private int inputLine;
    private Map<String, Integer> inputNumberMap;
    private Map<String, Integer> inputLineMap;
    private Map<String, String> triangleImagePathMap;


    @FXML
    public void initialize() {
        initializeInputNumberMap();
        initializeTriangleImagePathMap();
        initializeTriangleTypeSpinnerOptions();
    }

    private void setInputNumber(String triangleType){
        inputNumber = inputNumberMap.get(triangleType);
    }

    private void setInputLine(String triangleType){
        inputLine = inputLineMap.get(triangleType);
    }

    private void setTriangleImage(String triangleType){
        triangleImageView.setImage(new Image(triangleImagePathMap.get(triangleType)));
    }

    private void initializeInputNumberMap(){
        inputNumberMap = new HashMap<>();
        inputLineMap = new HashMap<>();
        inputNumberMap.put("Right triangle", 6);
        inputNumberMap.put("Arbitrary triangle", 6);
        inputLineMap.put("Right triangle", 3);
        inputLineMap.put("Arbitrary triangle", 4);
    }

    private void initializeTriangleImagePathMap(){
        triangleImagePathMap = new HashMap<>();
        triangleImagePathMap.put("Right triangle", "right_triangle.png");
        triangleImagePathMap.put("Arbitrary triangle", "arbitrary_triangle.png");
    }

    private void initializeTriangleTypeSpinnerOptions() {
        ObservableList<String> options = FXCollections.observableArrayList(
                "Arbitrary triangle", "Right triangle"

        );
        SpinnerValueFactory<String> valueFactory = new SpinnerValueFactory.ListSpinnerValueFactory<>(options);
        valueFactory.setValue("Right triangle");
        setTriangleImage("Right triangle");
        updateForm("Right triangle");

        triangleTypeSpinner.setValueFactory(valueFactory);
        triangleTypeSpinner.valueProperty().addListener((observable, oldValue, newValue) -> {
            setTriangleImage(newValue);
            updateForm(newValue);
        });
    }

    private void updateForm(String triangleType) {
        solutionBox.getChildren().clear();
        remarkHbox.getChildren().clear();
        setInputNumber(triangleType);
        setInputLine(triangleType);
        triangleSolverInputVbox.getChildren().clear();
        instructionLabel.getStyleClass().add("triangle-solution-label");
        loadDefaultInstruction(triangleType);

        List<TextField> textFields = new ArrayList<>();

        for (int i = 0; i < inputNumber; i++) {
            TextField textField = new TextField();
            textField.getStyleClass().add("coefficient-field");
            textFields.add(textField);
        }

        solveButton.setOnAction(actionEvent -> solve(triangleType, textFields));
        Label[] labels = generateLabels(triangleType, inputNumber);
        List<Node> nodes = createNodeArray(textFields, labels);
        triangleSolverInputVbox.getChildren().addAll(nodes);
    }

    private List<Node> createNodeArray(List<TextField> textFields, Label[] labels) {
        List<Node> nodes = new ArrayList<>();

        for (int i = 0; i < inputLine; i++) {
            HBox hbox = createGenericHbox();
            hbox.setPadding(new Insets(0, 0, 0, 20));
            hbox.getStyleClass().add("equation-solver-hbox");
            hbox.getChildren().addAll(createGenericHbox(), createGenericHbox());
            HBox child1 = (HBox) hbox.getChildren().get(0);
            HBox child2 = (HBox) hbox.getChildren().get(1);

            int j = i * 2;
            if(j < labels.length && j < textFields.size()){
                child1.getChildren().addAll(labels[j], textFields.get(j));
            }
            if(j + 1 < labels.length && j + 1 < textFields.size()){
                child2.getChildren().addAll(labels[j + 1], textFields.get(j + 1));
            }

            nodes.add(hbox);
        }

        return nodes;
    }

    private void generateSolution(String triangleType, List<Integer> indexes, List<String> input, List<Double> solution){
        List<HBox> hboxes = new ArrayList<>();
        HBox titleHbox = createGenericHbox();
        titleHbox.setStyle("-fx-background-color: #6db502;");
        titleHbox.setPadding(new Insets(5, 0, 5, 5));
        Label title = new Label("Solution");
        title.getStyleClass().add("equation-solution-title-label");
        titleHbox.getChildren().add(title);
        Label[] labels = generateLabels(triangleType, inputNumber);
        HBox introductionBox = createGenericHbox();
        introductionBox.setPadding(new Insets(0, 0, 0, 10));
        Label introductionLabel = createGenericLabel();
        introductionLabel.setText("Given");
        Label remarkLabel = new Label();
        remarkLabel.getStyleClass().add("triangle-remark-label");
        VBox vbox = new VBox();
        vbox.getStyleClass().add("triangle-solution-vbox");
        vbox.setPadding(new Insets(0, 0, 10, 0));
        List<HBox> finalSolution = new ArrayList<>();

        int gap = 2;
        int lines = 2;

        if(triangleType.equals(TriangleType.ARBITRARY.getSymbol())){
            gap = 3;
        }

        for(int i = 0; i < inputNumber + gap; i++){
            HBox hbox = createGenericHbox();
            hbox.setPadding(new Insets(0, 0, 0, 10));
            hboxes.add(hbox);
        }

        for(int i = 0; i < labels.length; i++){
            Label label = labels[i];
            if(label != null){
                label.setText(label.getText() + solution.get(i));
            }
        }


        for(int i = 0; i < input.size(); i++){
            introductionLabel.setText(introductionLabel.getText() + " " + labels[indexes.get(i)].getText());
            if(i < input.size() - 1){
                introductionLabel.setText(introductionLabel.getText() + " and");
            }
            else if(i == input.size() - 1){
                introductionLabel.setText(introductionLabel.getText() + " :");
            }
        }

        if(triangleType.equals(TriangleType.RIGHT.getSymbol()) && indexes.contains(4) && indexes.contains(5)){
            remarkLabel.setText("The problem has two solutions. The second solution is found by swapping " +
                                "a wigth b and 𝛼 with β.");
            remarkHbox.getChildren().add(remarkLabel);
        }

        introductionBox.getChildren().add(introductionLabel);

        vbox.getChildren().addAll(titleHbox, introductionBox);

        for(int i = 0; i < hboxes.size(); i++){
            HBox hbox = hboxes.get(i);
            if(!indexes.contains(i)){
                hbox.getChildren().addAll(labels[i]);
                finalSolution.add(hbox);
            }
        }

        for(int i = 0; i < lines; i++){
            HBox hbox = createGenericHbox();
            hbox.setStyle("-fx-alignment: center-left");
            hbox.setPadding(new Insets(0, 0, 0, 25));
            if(i*3 < finalSolution.size()){
                hbox.getChildren().addAll(finalSolution.get(i*3), new Label(" ;"));
            }
            if(i*3+1 < finalSolution.size()){
                hbox.getChildren().addAll(finalSolution.get(i*3+1), new Label(" ;"));
            }
            if(i*3+2 < finalSolution.size()){
                hbox.getChildren().addAll(finalSolution.get(i*3+2));
            }
            vbox.getChildren().add(hbox);
        }

        solutionBox.getChildren().add(vbox);
    }

    private Label[] generateLabels(String triangleType, int inputNumber) {
        Label[] labels = new Label[inputNumber + 2];
        if(triangleType.equals(TriangleType.ARBITRARY.getSymbol())){
            labels = new Label[inputNumber + 3];
        }
        switch (triangleType) {
            case "Right triangle":
                labels[0] = new Label("a = ");
                labels[1] = new Label("𝛼 = ");
                labels[2] = new Label("b = ");
                labels[3] = new Label("β = ");
                labels[4] = new Label("c = ");
                labels[5] = new Label("h = ");
                labels[6] = new Label("Perimeter = ");
                labels[7] = new Label("Area = ");
                break;
            case "Arbitrary triangle":
                labels[0] = new Label("a = ");
                labels[1] = new Label("𝛼 = ");
                labels[2] = new Label("b = ");
                labels[3] = new Label("β = ");
                labels[4] = new Label("c = ");
                labels[5] = new Label("૪ = ");
                labels[6] = new Label("h = ");
                labels[7] = new Label("Perimeter = ");
                labels[8] = new Label("Area = ");
                break;
            default:
                break;
        }

        for (Label label : labels) {
            if (label != null) {
                label.getStyleClass().add("equation-label");
                label.setStyle("-fx-margin-right: 5px");
            }
        }

        return labels;
    }

    private HBox createGenericHbox () {
        HBox hbox = new HBox();
        hbox.setStyle("-fx-alignment: center-left;");
        hbox.setPadding(new Insets(0, 0, 0, 0));
        return hbox;
    }

    private Label createGenericLabel(){
        Label label = new Label();
        label.getStyleClass().add("triangle-solution-label");
        return label;
    }

    private void solve(String type, List<TextField> textFields){
        try{
            checkTextFieldValue(textFields, type);
            loadDefaultInstruction(type);
            List<String> input = getLabelTextFromIndex(type, textFields);
            List<Double> features = new ArrayList<>();
            List<Integer> indexes = new ArrayList<>();
            for(int i = 0; i < textFields.size(); i++){
                if(!textFields.get(i).getText().isEmpty()){
                    indexes.add(i);
                    features.add(Double.parseDouble(textFields.get(i).getText()));
                }
                else{
                    features.add(-1.0);
                }
            }
            try{
                List<Double> solution = calculatorController.solveTriangle(type, features);
                generateSolution(type, indexes, input, solution);
            }catch (Exception e){
                instructionLabel.setStyle("-fx-text-fill: #d50202; -fx-font-weight: bold;");
                instructionLabel.setText(e.getMessage());
            }

        }catch(Exception e){
            instructionLabel.setStyle("-fx-text-fill: #d50202; -fx-font-weight: bold;");
            instructionLabel.setText(e.getMessage());
        }

    }


    private List<String> getLabelTextFromIndex(String type, List<TextField> textFields){
        List<String> labelTexts = new ArrayList<>();
        for(int i = 0; i < textFields.size(); i++){
            if(!textFields.get(i).getText().isEmpty()){
                labelTexts.add(findString(type, i));
            }
        }
        return labelTexts;
    }

    private String findString(String triangleType, int index) {
        String s = "";
        switch (triangleType) {
            case "Right triangle":
                switch (index) {
                    case 0:
                        s = "a";
                        break;
                    case 1:
                        s = "𝛼";
                        break;
                    case 2:
                        s = "b";
                        break;
                    case 3:
                        s = "β";
                        break;
                    case 4:
                        s = "c";
                        break;
                    case 5:
                        s = "h";
                        break;
                    default:
                        break;
                }
                break;
            case "Arbitrary triangle":
                switch (index) {
                    case 0:
                        s = "a";
                        break;
                    case 1:
                        s = "𝛼";
                        break;
                    case 2:
                        s = "b";
                        break;
                    case 3:
                        s = "β";
                        break;
                    case 4:
                        s = "c";
                        break;
                    case 5:
                        s = "૪";
                        break;
                    default:
                        break;
                }
                break;
            default:
                break;
        }
        return s;
    }

    private void checkTextFieldValue(List<TextField> textFields, String triangleType){
        int filled = 0;
        for(TextField textField : textFields){
            if(!textField.getText().isEmpty()){
                if(!containsOnlyDigits(textField.getText())){
                    throw new IllegalArgumentException("The field must contains only positive digits!");
                }
                if(Double.parseDouble(textField.getText()) <= 0){
                    throw new IllegalArgumentException("The field must contain positive numbers greater than 0!");
                }
                filled ++;
            }
            if(filled > 2 && triangleType.equals(TriangleType.RIGHT.getSymbol())){
                throw new IllegalArgumentException("Too much values given! Provide exactly 2 values to calculate the solution.");
            }
            else if(filled > 3 && triangleType.equals(TriangleType.ARBITRARY.getSymbol())){
                throw new IllegalArgumentException("Too much values given! Provide exactly 3 values to calculate the solution.");
            }
        }

        if(filled < 2 && triangleType.equals(TriangleType.RIGHT.getSymbol())){
            throw new IllegalArgumentException("You must provide 2 values to calculate the solution!");
        }
        else if(filled < 3 && triangleType.equals(TriangleType.ARBITRARY.getSymbol())){
            throw new IllegalArgumentException("You must provide 3 values to calculate the solution!");
        }

    }

    private void loadDefaultInstruction(String type){
        instructionLabel.setStyle("-fx-text-fill: #000000; -fx-font-weight: normal;");
        switch (type){
            case "Right triangle":
                instructionLabel.setText("Please provide 2 values below to calculate the other values of a right triangle :");
                break;
            case "Arbitrary triangle":
                instructionLabel.setText("Please provide 3 values including at least one side to the following fields :");
                break;
            default:
                break;
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

            if (!Character.isDigit(value.charAt(i)) && !(value.charAt(i) == '.')) {
                return false;
            }
        }
        return true;
    }
}