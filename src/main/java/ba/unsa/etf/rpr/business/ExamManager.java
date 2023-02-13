package ba.unsa.etf.rpr.business;

import ba.unsa.etf.rpr.auxiliary.AlertThrower;
import ba.unsa.etf.rpr.dao.DaoFactory;
import ba.unsa.etf.rpr.domain.Course;
import ba.unsa.etf.rpr.domain.Exam;
import ba.unsa.etf.rpr.domain.User;
import ba.unsa.etf.rpr.exception.DBHandleException;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

/**
 * Business layer manager for Exam.
 *
 */
public class ExamManager {

    public List<Exam> searchExam(String courseName, LocalDate date) {
        try {
            List<Exam> exams = DaoFactory.examDao().getAll();

            for (Exam exam : exams) {
                if (!courseName.isEmpty() && !exam.getCourse().getName().equals(courseName)) {
                    exams.remove(exam);
                }

                if (date != null && !exam.getExamTime().equals(new Date(date.toEpochDay()))) {
                    exams.remove(exam);
                }
            }

            return exams;
        } catch (DBHandleException e) {
            AlertThrower.throwAlert(e, Alert.AlertType.ERROR);
        }

        return null;
    }

    public Exam createExam(String courseName, User user, LocalDate date, String answerSheet) {
        if (courseName.isEmpty() || date == null) {
            return null;
        }

        try {
            Course course = DaoFactory.courseDao().searchByName(courseName);

            if (course == null) {
                new Alert(Alert.AlertType.ERROR, "Given course does not exist", ButtonType.OK);

                return null;
            }

            Exam exam = new Exam();

            exam.setCourse(course);
            exam.setUser(user);
            exam.setExamTime(new Date(date.toEpochDay()));
            exam.setAnswerSheet(answerSheet);

            return exam;
        } catch (DBHandleException e) {
            AlertThrower.throwAlert(e, Alert.AlertType.ERROR);
        }

        return null;
    }

}
