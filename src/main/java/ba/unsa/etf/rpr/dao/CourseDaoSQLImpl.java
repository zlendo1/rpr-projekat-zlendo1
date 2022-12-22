package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Course;
import ba.unsa.etf.rpr.exception.DBHandleException;

import java.sql.ResultSet;
import java.util.List;
import java.util.Map;

public class CourseDaoSQLImpl extends AbstractDao<Course> implements CourseDao{

    public CourseDaoSQLImpl() throws DBHandleException {
        super("course");
    }

    /**
     * Extracts a bean from a ResultSet object.
     *
     * @param resultSet Contains the row we want to extract
     * @return Bean object
     * @throws DBHandleException In case of errors while working with ResultSet
     */
    @Override
    public Course rowToObject(ResultSet resultSet) throws DBHandleException {
        return null;
    }

    /**
     * Get row utilising a Map object from a bean object.
     * The keys are the names of the bean's attributes.
     *
     * @param object Bean object which we want converted
     * @return Map object containing bean
     */
    @Override
    public Map<String, Object> objectToRow(Course object) {
        return null;
    }

    /**
     * Search for courses in DB by its name.
     *
     * @param name Course name
     * @return List of courses
     */
    @Override
    public List<Course> searchByName(String name) throws DBHandleException {
        return null;
    }

    /**
     * Search for courses in DB by its professor's name.
     *
     * @param name A professor's full name
     * @return List of courses
     */
    @Override
    public List<Course> searchByProfessor(String name) throws DBHandleException {
        return null;
    }

}
