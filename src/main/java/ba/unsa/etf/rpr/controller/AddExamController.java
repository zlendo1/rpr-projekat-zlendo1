package ba.unsa.etf.rpr.controller;

import ba.unsa.etf.rpr.business.ExamManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.text.Text;

/**
 * Controller class for the exam addition scene.
 *
 */
public class AddExamController {

    // GUI objects
    public TextArea answerSheetField;
    public Text courseNameFieldError;
    public Text examTimeFieldError;
    public Text answerSheetFieldError;
    public DatePicker examTimeField;

    // Manager objects
    private final ExamManager manager = new ExamManager();

    @FXML
    public void initialize() {
    }

    public void cancel(ActionEvent actionEvent) {
    }

    public void add(ActionEvent actionEvent) {
    }

}
