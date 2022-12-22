package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.exception.DBHandleException;
import ba.unsa.etf.rpr.domain.Exam;
import ba.unsa.etf.rpr.domain.Course;
import ba.unsa.etf.rpr.domain.User;

import java.sql.*;
import java.util.*;
import java.util.Date;

/**
 * SQL implementation of ExamDao
 *
 */
public class ExamDaoSQLImpl extends AbstractDao<Exam> implements ExamDao {

    public ExamDaoSQLImpl() throws DBHandleException {
        super("exam");
    }

    /**
     * Extracts a bean from a ResultSet object.
     *
     * @param resultSet Contains the row we want to extract
     * @return Bean object
     * @throws DBHandleException In case of errors while working with ResultSet
     */
    @Override
    public Exam rowToObject(ResultSet resultSet) throws DBHandleException {
        try {
            return new Exam(
                    resultSet.getInt("id"),
                    DaoFactory.userDao().getById(resultSet.getInt("user_id")),
                    DaoFactory.courseDao().getById(resultSet.getInt("course_id")),
                    resultSet.getTimestamp("exam_time"),
                    resultSet.getString("answer_sheet")
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
    public Map<String, Object> objectToRow(Exam object) {
        Map<String, Object> row = new TreeMap<>();

        row.put("id", object.getId());
        row.put("user_id", object.getUser().getId());
        row.put("course_id", object.getCourse().getId());
        row.put("exam_time", object.getExamTime());
        row.put("answer_sheet", object.getAnswerSheet());

        return row;
    }

    /**
     * Search for exams in DB by user.
     *
     * @param user The user that provided the exams
     * @return List of exams
     * @throws DBHandleException In case of any DB handling error
     */
    @Override
    public List<Exam> getByUser(User user) throws DBHandleException {
        List<Exam> subscriptionList = new ArrayList<>();

        String query = "SELECT * FROM exam WHERE user_id = ?";

        try {
            PreparedStatement preparedStatement = getConnection().prepareStatement(query);

            preparedStatement.setInt(1, user.getId());

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                subscriptionList.add(rowToObject(resultSet));
            }

            resultSet.close();

        } catch (SQLException e) {
            throw new DBHandleException(e);
        }

        return subscriptionList;
    }

    /**
     * Search for exams in DB by course.
     *
     * @param course The course the exam belongs to
     * @return List of exams
     * @throws DBHandleException In case of any DB handling error
     */
    @Override
    public List<Exam> getByCourse(Course course) throws DBHandleException {
        return null;
    }

    /**
     * Search for exams in DB by exam time
     *
     * @param examTime Date and time of the exam
     * @return List of exams
     * @throws DBHandleException In case of any DB handling error
     */
    @Override
    public List<Exam> getByExamTime(Date examTime) throws DBHandleException {
        List<Exam> subscriptionList = new ArrayList<>();

        String query = "SELECT * FROM exam WHERE exam_time = ?";

        try {
            PreparedStatement preparedStatement = getConnection().prepareStatement(query);

            preparedStatement.setTimestamp(1, (Timestamp) examTime);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                subscriptionList.add(rowToObject(resultSet));
            }

            resultSet.close();

        } catch (SQLException e) {
            throw new DBHandleException(e);
        }

        return subscriptionList;
    }

}
