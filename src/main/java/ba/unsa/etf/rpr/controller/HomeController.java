package ba.unsa.etf.rpr.controller;

import ba.unsa.etf.rpr.domain.Course;
import ba.unsa.etf.rpr.domain.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;

import java.util.Date;

/**
 * Controller class for the home screen.
 *
 */
public class HomeController {

    public TextField courseNameSearch;
    public DatePicker examTimeSearch;
    public TableColumn<User, String> usernameColumn;
    public TableColumn<Course, String> courseNameColumn;
    public TableColumn<Course, String> professorColumn;
    public TableColumn<Course, Date> examTimeColumn;
    public TableColumn<Course, String> answerSheetColumn;

    @FXML
    public void initialize() {
    }

    public void searchExams(ActionEvent actionEvent) {
    }

    public void addExam(ActionEvent actionEvent) {
    }

    public void addCourse(ActionEvent actionEvent) {
    }

}
