package ba.unsa.etf.rpr.controller;

import ba.unsa.etf.rpr.auxiliary.AlertThrower;
import ba.unsa.etf.rpr.business.ExamManager;
import ba.unsa.etf.rpr.domain.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import static ba.unsa.etf.rpr.auxiliary.AlertThrower.addEmptyFieldError;

/**
 * Controller class for the exam addition scene.
 *
 */
public class AddExamController {

    // GUI objects
    public TextField courseNameField;
    public DatePicker examTimeField;
    public TextArea answerSheetField;
    public Text courseNameFieldError;
    public Text examTimeFieldError;
    public Text answerSheetFieldError;

    // Manager objects
    private final ExamManager manager = new ExamManager();

    // User bean
    private final User user;

    public AddExamController(User user) {
        this.user = user;
    }

    @FXML
    public void initialize() {
        addEmptyFieldError(co);
    }

    public void cancel(ActionEvent actionEvent) {
    }

    public void add(ActionEvent actionEvent) {
    }

}
