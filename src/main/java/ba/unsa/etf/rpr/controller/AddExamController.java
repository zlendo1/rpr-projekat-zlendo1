package ba.unsa.etf.rpr.controller;

import ba.unsa.etf.rpr.auxiliary.AlertThrower;
import ba.unsa.etf.rpr.auxiliary.SceneLoader;
import ba.unsa.etf.rpr.business.ExamManager;
import ba.unsa.etf.rpr.domain.Exam;
import ba.unsa.etf.rpr.domain.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

import static ba.unsa.etf.rpr.auxiliary.AlertThrower.addEmptyTextFieldError;

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
        addEmptyTextFieldError(courseNameField, courseNameFieldError, "Course name must exist");

        examTimeField.valueProperty().addListener(
                (observer, oldValue, newValue) -> {
                    if (newValue == null) {
                        examTimeFieldError.setText("Exam date must exist");
                    }
                }
        );
    }

    public void cancel(ActionEvent actionEvent) {
        Stage stage = (Stage) courseNameField.getScene().getWindow();

        try {
            SceneLoader.load(stage, "home", "Home", new HomeController(user), true);
        } catch (IOException e) {
            AlertThrower.throwAlert(e, Alert.AlertType.ERROR);
        }
    }

    public void add(ActionEvent actionEvent) {
        Exam exam = manager.createExam(courseNameField.getText(), user, examTimeField.getValue(), answerSheetField.getText());

        System.out.println(exam);

        if (exam == null) {
            return;
        }

        Stage stage = (Stage) courseNameField.getScene().getWindow();

        try {
            SceneLoader.load(stage, "home", "Home", new HomeController(user), true);
        } catch (IOException e) {
            AlertThrower.throwAlert(e, Alert.AlertType.ERROR);
        }
    }

}
