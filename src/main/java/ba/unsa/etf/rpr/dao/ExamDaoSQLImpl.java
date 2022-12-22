package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Course;
import ba.unsa.etf.rpr.domain.User;
import ba.unsa.etf.rpr.exception.DBHandleException;
import ba.unsa.etf.rpr.connector.MyConnection;
import ba.unsa.etf.rpr.domain.Exam;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * SQL implementation of ExamDao
 *
 */
public class ExamDaoSQLImpl extends AbstractDao<Exam> implements ExamDao {

    public ExamDaoSQLImpl() throws DBHandleException {
        super("exam");
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
        return null;
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
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            preparedStatement.setTimestamp(1, (Timestamp) examTime);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                subscriptionList.add(new Exam(
                        resultSet.getInt("id"),
                        new ProviderDaoSQLImpl().getById(resultSet.getInt("provider_id")),
                        resultSet.getString("course_name"),
                        resultSet.getTimestamp("exam_time"),
                        resultSet.getString("answer_sheet")
                ));
            }

            resultSet.close();

        } catch (Exception e) {
            throw new DBHandleException(e);
        }

        return subscriptionList;
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
    public Map<String, Object> objectToRow(Exam object) {
        return null;
    }
}
