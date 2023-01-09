package ba.unsa.etf.rpr.controller;

import ba.unsa.etf.rpr.business.CourseManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

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

    @FXML
    public void initialize() {
    }

    public void add(ActionEvent actionEvent) {
    }

    public void cancel(ActionEvent actionEvent) {
    }

}
