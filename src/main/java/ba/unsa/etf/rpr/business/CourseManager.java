package ba.unsa.etf.rpr.business;

import ba.unsa.etf.rpr.dao.DaoFactory;
import ba.unsa.etf.rpr.domain.Course;
import ba.unsa.etf.rpr.exception.DBHandleException;
import javafx.scene.control.Alert;

/**
 * Business layer manager for Course.
 *
 */
public class CourseManager {

    public Course createCourse(String courseName, String professor) throws DBHandleException {
        if (courseName.isEmpty() || professor.isEmpty()) {
            return null;
        }

        if (DaoFactory.courseDao().searchByName(courseName) != null) {
            throw new DBHandleException("Course name occupied");
        }

        Course course = new Course();

        course.setName(courseName);
        course.setProfessor(professor);

        return DaoFactory.courseDao().add(course);
    }

}
