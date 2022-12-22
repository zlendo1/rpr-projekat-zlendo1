package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Course;
import ba.unsa.etf.rpr.domain.User;
import ba.unsa.etf.rpr.exception.DBHandleException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

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
        try {
            return new Course(
                    resultSet.getInt("id"),
                    resultSet.getString("name"),
                    resultSet.getString("professor")
            );

        } catch (SQLException e) {
            throw new DBHandleException(e);
        }
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
        Map<String, Object> row = new TreeMap<>();

        row.put("id", object.getId());
        row.put("name", object.getName());
        row.put("professor", object.getProfessor());

        return row;
    }

    /**
     * Search for courses in DB by its name.
     *
     * @param name Course name
     * @return List of courses
     */
    @Override
    public List<Course> searchByName(String name) throws DBHandleException {
        List<Course> courseList = new ArrayList<>();

        String query = "SELECT * FROM course WHERE name = ?";

        try {
            PreparedStatement preparedStatement = getConnection().prepareStatement(query);

            preparedStatement.setString(1, name);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                courseList.add(new Course(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("professor")
                ));
            }

            resultSet.close();

        } catch (SQLException e) {
            throw new DBHandleException(e);
        }

        return courseList;
    }

    /**
     * Search for courses in DB by its professor's name.
     *
     * @param professor A professor's full name
     * @return List of courses
     */
    @Override
    public List<Course> searchByProfessor(String professor) throws DBHandleException {
        List<Course> courseList = new ArrayList<>();

        String query = "SELECT * FROM course WHERE professor = ?";

        try {
            PreparedStatement preparedStatement = getConnection().prepareStatement(query);

            preparedStatement.setString(1, professor);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                courseList.add(new Course(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("professor")
                ));
            }

            resultSet.close();

        } catch (SQLException e) {
            throw new DBHandleException(e);
        }

        return courseList;
    }

}
