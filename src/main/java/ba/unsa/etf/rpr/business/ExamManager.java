package ba.unsa.etf.rpr.business;

import ba.unsa.etf.rpr.dao.DaoFactory;
import ba.unsa.etf.rpr.domain.Course;
import ba.unsa.etf.rpr.domain.Exam;
import ba.unsa.etf.rpr.domain.User;
import ba.unsa.etf.rpr.exception.DBHandleException;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.ListIterator;

/**
 * Business layer manager for Exam.
 *
 */
public class ExamManager {

    /**
     * Filters exams by paramaters
     *
     * @param courseName Name of the course the exam belongs to. If empty, will be disregarded. Must not be null.
     * @param date Date of the exam. If null, will be disregarded.
     * @return List of filtered Exams
     * @throws DBHandleException In case of any DB errors
     */
    public List<Exam> searchExam(String courseName, LocalDate date) throws DBHandleException {
        List<Exam> exams = DaoFactory.examDao().getAll();

        for (ListIterator<Exam> iter = exams.listIterator(); iter.hasNext();) {
            Exam exam = iter.next();

            if (!courseName.isEmpty() && !exam.getCourse().getName().equals(courseName)) {
                iter.remove();
            } else if (date != null && !exam.getExamTime().equals(localDateToDate(date))) {
                iter.remove();
            }
        }

        return exams;
    }

    /**
     * Creates exam with the given arguments.
     *
     * @param courseName Name of the course the exam belongs to.
     * @param user User bean of which is adding the Exam.
     * @param date The date the exam was held.
     * @param answerSheet The answer sheet if the exam.
     * @return A freshly created Exam bean.
     * @throws DBHandleException In case of any DB errors.
     */
    public Exam createExam(String courseName, User user, LocalDate date, String answerSheet) throws DBHandleException {
        if (courseName.isEmpty() || date == null) {
            return null;
        }

        Course course = DaoFactory.courseDao().searchByName(courseName);

        if (course == null) {
            throw new DBHandleException("Given course does not exist");
        }

        Exam exam = new Exam();

        exam.setCourse(course);
        exam.setUser(user);
        exam.setExamTime(localDateToDate(date));
        exam.setAnswerSheet(answerSheet);

        return DaoFactory.examDao().add(exam);
    }

    /**
     * A method for listing items from the entire DB. Very intuitive.
     *
     * @return List of all the exams from our DB.
     * @throws DBHandleException In case of any DB errors.
     */
    public List<Exam> getAll() throws DBHandleException {
        return DaoFactory.examDao().getAll();
    }

    private Date localDateToDate(LocalDate localDate) {
        return Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }

}
