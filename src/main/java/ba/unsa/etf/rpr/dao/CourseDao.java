package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Course;
import ba.unsa.etf.rpr.exception.DBHandleException;

import java.util.List;

/**
 * Dao interface for Course bean.
 */
public interface CourseDao extends Dao<Course> {

    /**
     * Search for courses in DB by its name.
     *
     * @param name Course name
     * @return List of courses
     */
    List<Course> searchByName(String name) throws DBHandleException;

    /**
     * Search for courses in DB by its professor's name.
     *
     * @param name A professor's full name
     * @return List of courses
     */
    List<Course> searchByProfessor(String name) throws DBHandleException;

}
