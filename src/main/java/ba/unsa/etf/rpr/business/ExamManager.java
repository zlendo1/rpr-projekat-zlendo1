package ba.unsa.etf.rpr.business;

import ba.unsa.etf.rpr.auxiliary.AlertThrower;
import ba.unsa.etf.rpr.dao.DaoFactory;
import ba.unsa.etf.rpr.domain.Exam;
import ba.unsa.etf.rpr.exception.DBHandleException;
import javafx.scene.control.Alert;

import java.util.Date;
import java.util.List;

/**
 * Business layer manager for Exam.
 *
 */
public class ExamManager {

    public List<Exam> searchExam(String courseName, Date date) {
        try {
            List<Exam> exams = DaoFactory.examDao().getAll();

            for (Exam exam : exams) {
                if (!courseName.isEmpty() && !exam.getCourse().getName().equals(courseName)) {
                    exams.remove(exam);
                }

                if (date != null && !exam.getExamTime().equals(date)) {
                    exams.remove(exam);
                }
            }

            return exams;
        } catch (DBHandleException e) {
            AlertThrower.throwAlert(e, Alert.AlertType.ERROR);
        }

        return null;
    }

}
