package ba.unsa.etf.rpr.controller;

import ba.unsa.etf.rpr.auxiliary.AlertThrower;
import ba.unsa.etf.rpr.auxiliary.SceneLoader;
import ba.unsa.etf.rpr.business.CourseManager;
import ba.unsa.etf.rpr.domain.Course;
import ba.unsa.etf.rpr.domain.User;
import ba.unsa.etf.rpr.exception.DBHandleException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

import static ba.unsa.etf.rpr.auxiliary.AlertThrower.addEmptyTextFieldError;

/**
 * Controller class for the course addition scene.
 *
 */
public class AddCourseController {

    // GUI objects
    public TextField courseNameField;
    public TextField professorField;
    public Text courseNameErrorField;
    public Text professorErrorField;

    // Manager objects
    private final CourseManager manager = new CourseManager();

    // User bean
    private final User user;

    public AddCourseController(User user) {
        this.user = user;
    }

    @FXML
    public void initialize() {
        addEmptyTextFieldError(courseNameField, courseNameErrorField, "Course name must exist");
        addEmptyTextFieldError(professorField, professorErrorField, "Professor name must exist");
    }

    public void add(ActionEvent actionEvent) {
        try {
            Course course = manager.createCourse(courseNameField.getText(), professorField.getText());

            if (course == null) {
                return;
            }

            Stage stage = (Stage) courseNameField.getScene().getWindow();

            SceneLoader.load(stage, "home", "Home", new HomeController(user), true);
        } catch (IOException | DBHandleException e) {
            AlertThrower.throwAlert(e, Alert.AlertType.ERROR);
        }
    }

    public void cancel(ActionEvent actionEvent) {
        Stage stage = (Stage) courseNameField.getScene().getWindow();

        try {
            SceneLoader.load(stage, "home", "Home", new HomeController(user), true);
        } catch (IOException e) {
            AlertThrower.throwAlert(e, Alert.AlertType.ERROR);
        }
    }

}
