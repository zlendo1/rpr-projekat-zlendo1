package ba.unsa.etf.rpr.business;

import ba.unsa.etf.rpr.auxiliary.AlertThrower;
import ba.unsa.etf.rpr.dao.DaoFactory;
import ba.unsa.etf.rpr.domain.Course;
import ba.unsa.etf.rpr.exception.DBHandleException;
import javafx.scene.control.Alert;

/**
 * Business layer manager for Course.
 *
 */
public class CourseManager {

    public Course createCourse(String courseName, String professor) {
        if (courseName.isEmpty() || professor.isEmpty()) {
            return null;
        }

        try {
            if (DaoFactory.courseDao().searchByName(courseName) != null) {
                new Alert(Alert.AlertType.ERROR, "Course name occupied");

                return null;
            }

            Course course = new Course();

            course.setName(courseName);
            course.setProfessor(professor);

            return DaoFactory.courseDao().add(course);
        } catch (DBHandleException e) {
            AlertThrower.throwAlert(e, Alert.AlertType.ERROR);
        }

        return null;
    }

}
