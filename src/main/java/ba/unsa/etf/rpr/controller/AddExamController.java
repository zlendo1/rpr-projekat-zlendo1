package ba.unsa.etf.rpr.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.text.Text;

/**
 * Controller class for the exam addition scene.
 *
 */
public class AddExamController {

    public TextArea answerSheetField;
    public Text courseNameFieldError;
    public Text examTimeFieldError;
    public Text answerSheetFieldError;
    public DatePicker examTimeField;
    public Button cancelButton;
    public Button addButton;

    @FXML
    public void initialize() {
    }

    public void cancelButtonAction(ActionEvent actionEvent) {
    }

    public void addButtonAction(ActionEvent actionEvent) {
    }

}
