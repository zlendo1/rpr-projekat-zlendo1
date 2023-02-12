package ba.unsa.etf.rpr.controller;

import ba.unsa.etf.rpr.business.ExamManager;
import ba.unsa.etf.rpr.domain.Course;
import ba.unsa.etf.rpr.domain.Exam;
import ba.unsa.etf.rpr.domain.User;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.Date;
import java.util.List;

/**
 * Controller class for the home screen.
 *
 */
public class HomeController {

    // GUI objects
    public TextField courseNameSearch;
    public DatePicker examTimeSearch;
    public TableView<Exam> table;
    public TableColumn<Exam, String> usernameColumn;
    public TableColumn<Exam, String> courseNameColumn;
    public TableColumn<Exam, String> professorColumn;
    public TableColumn<Exam, Date> examTimeColumn;
    public TableColumn<Exam, String> answerSheetColumn;

    // Manager objects
    private final ExamManager manager = new ExamManager();

    // User bean
    private User user;

    public HomeController(User user) {
        this.user = user;

        usernameColumn.setCellValueFactory(new PropertyValueFactory<>("username"));
        courseNameColumn.setCellValueFactory(new PropertyValueFactory<>("courseName"));
        professorColumn.setCellValueFactory(new PropertyValueFactory<>("professor"));
        examTimeColumn.setCellValueFactory(new PropertyValueFactory<>("examTime"));
        answerSheetColumn.setCellValueFactory(new PropertyValueFactory<>("answerSheet"));
    }

    @FXML
    public void initialize() {
        List<Exam> examList = manager.searchExam(null, null);

        table.setItems(FXCollections.observableList(examList));
    }

    public void searchExams(ActionEvent actionEvent) {

    }

    public void addExam(ActionEvent actionEvent) {
    }

    public void addCourse(ActionEvent actionEvent) {
    }

}
