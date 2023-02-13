package ba.unsa.etf.rpr.controller;

import ba.unsa.etf.rpr.auxiliary.AlertThrower;
import ba.unsa.etf.rpr.auxiliary.SceneLoader;
import ba.unsa.etf.rpr.business.ExamManager;
import ba.unsa.etf.rpr.dao.DaoFactory;
import ba.unsa.etf.rpr.domain.Exam;
import ba.unsa.etf.rpr.domain.User;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
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
    private final User user;

    public HomeController(User user) {
        this.user = user;
    }

    @FXML
    public void initialize() {
        usernameColumn.setCellValueFactory(new PropertyValueFactory<>("username"));
        courseNameColumn.setCellValueFactory(new PropertyValueFactory<>("courseName"));
        professorColumn.setCellValueFactory(new PropertyValueFactory<>("professor"));
        examTimeColumn.setCellValueFactory(new PropertyValueFactory<>("examTime"));
        answerSheetColumn.setCellValueFactory(new PropertyValueFactory<>("answerSheet"));

        List<Exam> examList = manager.getAll();

        updateTable(examList);
    }

    public void searchExams(ActionEvent actionEvent) {
        List<Exam> examList = manager.searchExam(courseNameSearch.getText(), examTimeSearch.getValue());

        updateTable(examList);
    }

    public void addExam(ActionEvent actionEvent) {
        Stage stage = (Stage) table.getScene().getWindow();

        try {
            SceneLoader.load(stage, "addExam", "Add exam", new AddExamController(user), false);
        } catch (IOException e) {
            AlertThrower.throwAlert(e, Alert.AlertType.ERROR);
        }
    }

    public void addCourse(ActionEvent actionEvent) {
        Stage stage = (Stage) table.getScene().getWindow();

        try {
            SceneLoader.load(stage, "addCourse", "Add course", new AddCourseController(user), false);
        } catch (IOException e) {
            AlertThrower.throwAlert(e, Alert.AlertType.ERROR);
        }
    }

    private void updateTable(List<Exam> examList) {
        table.setItems(FXCollections.observableList(examList));

        table.refresh();
    }

}
