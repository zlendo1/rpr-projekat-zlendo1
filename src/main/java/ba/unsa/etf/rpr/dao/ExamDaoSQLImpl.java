package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.exception.DBHandleException;
import ba.unsa.etf.rpr.connector.MyConnection;
import ba.unsa.etf.rpr.domain.Exam;
import ba.unsa.etf.rpr.domain.Provider;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * SQL implementation of ExamDao
 *
 */
public class ExamDaoSQLImpl implements ExamDao {

    private final Connection connection;

    /**
     * Establishes connection to the DB
     *
     */
    public ExamDaoSQLImpl() throws DBHandleException {
        connection = MyConnection.getInstance().getConnection();
    }

    /**
     * Get entity from database coresponding to its primary key
     *
     * @param id primary key of entity
     * @return corresponding entity
     * @throws DBHandleException In case of any DB handling error
     */
    @Override
    public Exam getById(int id) throws DBHandleException {
        String query = "SELECT * FROM exam WHERE exam_id = ?";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                Exam exam = new Exam(
                        resultSet.getInt("exam_id"),
                        new ProviderDaoSQLImpl().getById(resultSet.getInt("provider_id")),
                        resultSet.getString("course_name"),
                        resultSet.getTimestamp("exam_time"),
                        resultSet.getString("answer_sheet")
                );

                resultSet.close();

                return exam;
            }

        } catch (Exception e) {
            throw new DBHandleException(e);
        }

        return null;
    }

    /**
     * Saves entity into database
     *
     * @param item bean for saving into database
     * @return updated version of the bean
     * @throws DBHandleException In case of any DB handling error
     */
    @Override
    public Exam add(Exam item) throws DBHandleException {
        String insert = "INSERT INTO exam(provider_id, course_name, exam_time, answer_sheet) VALUES(?, ?, ?, ?. ?)";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setInt(1, item.getProvider().getUser().getId());
            preparedStatement.setString(2, item.getCourseName());
            preparedStatement.setTimestamp(3, (Timestamp) item.getExamTime());
            preparedStatement.setString(4, item.getAnswerSheet());

            preparedStatement.executeUpdate();

            ResultSet resultSet = preparedStatement.getGeneratedKeys();

            resultSet.next();

            item.setId(resultSet.getInt(1));

            resultSet.close();

            return item;

        } catch (Exception e) {
            throw new DBHandleException(e);
        }
    }

    /**
     * Updates entity from database based on its primary key
     *
     * @param item bean which we will update (id must be populated)
     * @return updated version of the bean
     * @throws DBHandleException In case of any DB handling error
     */
    @Override
    public Exam update(Exam item) throws DBHandleException {
        String update = "UPDATE exam SET provider_id = ?, course_name = ?, exam_time = ?, answer_sheet = ? " +
                "WHERE exam_id = ?";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(update);

            preparedStatement.setInt(1, item.getProvider().getUser().getId());
            preparedStatement.setString(2, item.getCourseName());
            preparedStatement.setTimestamp(3, (Timestamp) item.getExamTime());
            preparedStatement.setString(4, item.getAnswerSheet());

            preparedStatement.executeUpdate();

            preparedStatement.close();

            return item;

        } catch (Exception e) {
            throw new DBHandleException(e);
        }
    }

    /**
     * Hard delete of entity with the corseponding primary key
     *
     * @param id primary key of the entity
     * @throws DBHandleException In case of any DB handling error
     */
    @Override
    public void delete(int id) throws DBHandleException {
        String delete = "DELETE FROM exam WHERE exam_id = ?";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(delete);

            preparedStatement.setInt(1, id);

            preparedStatement.executeUpdate();

            preparedStatement.close();

        } catch (SQLException e) {
            throw new DBHandleException(e);
        }
    }

    /**
     * Lists all entites from the database
     *
     * @return list of entities from the database
     * @throws DBHandleException In case of any DB handling error
     */
    @Override
    public List<Exam> getAll() throws DBHandleException {
        List<Exam> subscriptionList = new ArrayList<>();

        String query = "SELECT * FROM exam";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                subscriptionList.add(new Exam(
                        resultSet.getInt("exam_id"),
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
     * Search for exams in DB by provider
     *
     * @param provider The provider that provided the exams
     * @return List of exams
     * @throws DBHandleException In case of any DB handling error
     */
    @Override
    public List<Exam> getByProvider(Provider provider) throws DBHandleException {
        List<Exam> subscriptionList = new ArrayList<>();

        String query = "SELECT * FROM exam WHERE provider_id = ?";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            preparedStatement.setInt(1, provider.getUser().getId());

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                subscriptionList.add(new Exam(
                        resultSet.getInt("exam_id"),
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
     * Search for exams in DB by course name
     *
     * @param courseName Name of the course the exam belongs to
     * @return List of exams
     * @throws DBHandleException In case of any DB handling error
     */
    @Override
    public List<Exam> getByCourseName(String courseName) throws DBHandleException {
        List<Exam> subscriptionList = new ArrayList<>();

        String query = "SELECT * FROM exam WHERE course_name = ?";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            preparedStatement.setString(1, courseName);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                subscriptionList.add(new Exam(
                        resultSet.getInt("exam_id"),
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
                        resultSet.getInt("exam_id"),
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

}
