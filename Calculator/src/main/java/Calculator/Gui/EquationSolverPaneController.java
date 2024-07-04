package Calculator.Gui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.text.Text;

import java.util.HashMap;
import java.util.Map;

public class EquationSolverPaneController {

    @FXML
    private Spinner<String> equationTypeSpinner;

    @FXML
    private Text equationFormText;

    private Map<String, String> equationForms;

    @FXML
    public void initialize() {
        initializeEquationFormMap();
        initializeEquationTypeSpinnerOptions();
    }

    private void initializeEquationFormMap(){
        equationForms = new HashMap<>();
        equationForms.put("Linear Equation", "Ax + B = 0");
        equationForms.put("Quadratic Equation", "Ax² + Bx + C = 0");
        equationForms.put("system of 2 equations with 2 unknowns", "{\n" + "  A₁ x  +  B₁ y = C₁\n" +
                "  A₂ x  +  B₂ y = C₂\n" + "}");
        equationForms.put("system of 3 equations with 3 unknowns", "{\n" +
                "  A₁ x  +  B₁ y  +  C₁ z = D₁\n" +
                "  A₂ x  +  B₂ y  +  C₂ z = D₂\n" +
                "  A₃ x  +  B₃ y  +  C₃ z = D₃\n" +
                "}");
    }

    private void initializeEquationTypeSpinnerOptions() {
        ObservableList<String> options = FXCollections.observableArrayList(
                "Linear Equation", "Quadratic Equation", "system of 2 equations with 2 unknowns",
                     "system of 3 equations with 3 unknowns"
        );
        SpinnerValueFactory<String> valueFactory = new SpinnerValueFactory.ListSpinnerValueFactory<>(options);
        valueFactory.setValue("Linear Equation");
        equationFormText.setText("Ax + B = 0");
        equationTypeSpinner.setValueFactory(valueFactory);
        equationTypeSpinner.valueProperty().addListener((observable, oldValue, newValue) -> {
            setEquationFormText(newValue);
        });
    }

    private void setEquationFormText(String equationType){
        equationFormText.setText(equationForms.get(equationType));
    }

}
