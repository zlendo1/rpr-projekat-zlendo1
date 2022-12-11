package unsa.etf.rpr.dao;

import unsa.etf.rpr.connector.MyConnection;
import unsa.etf.rpr.domain.Exam;
import unsa.etf.rpr.domain.Provider;
import unsa.etf.rpr.exception.DBHandleException;

import java.sql.*;
import java.util.Date;
import java.util.List;

public class ExamDaoSQLImpl implements ExamDao {

    private final Connection connection;

    /**
     * Establishes connection to the DB
     *
     */
    public ExamDaoSQLImpl() throws DBHandleException {
        connection = MyConnection.EstablishConnection();
    }

    /**
     * Get entity from database coresponding to its primary key
     *
     * @param id primary key of entity
     * @return corresponding entity
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
     */
    @Override
    public Exam add(Exam item) throws DBHandleException {
        String insert = "INSERT INTO exam(provider_id, course_name, exam_time, answer_sheet) VALUES(?, ?, ?, ?. ?)";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setInt(1, item.getProvider().getPerson().getPersonId());
            preparedStatement.setString(2, item.getCourseName());
            preparedStatement.setTimestamp(3, (Timestamp) item.getExamTime());
            preparedStatement.setString(4, item.getAnswerSheet());

            preparedStatement.executeUpdate();

            ResultSet resultSet = preparedStatement.getGeneratedKeys();

            resultSet.next();

            item.setExamId(resultSet.getInt(1));

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
     */
    @Override
    public Exam update(Exam item) throws DBHandleException {
        String update = "UPDATE exam SET provider_id = ?, course_name = ?, exam_time = ?, answer_sheet = ? " +
                "WHERE exam_id = ?";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(update);

            preparedStatement.setInt(1, item.getProvider().getPerson().getPersonId());
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
     */
    @Override
    public List<Exam> getAll() {
        return null;
    }

    /**
     * Search for exams in DB by provider
     *
     * @param provider The provider that provided the exams
     * @return List of exams
     */
    @Override
    public List<Exam> getByProvider(Provider provider) {
        return null;
    }

    /**
     * Search for exams in DB by course name
     *
     * @param courseName Name of the course the exam belongs to
     * @return List of exams
     */
    @Override
    public List<Exam> getByCourseName(String courseName) {
        return null;
    }

    /**
     * Search for exams in DB by exam time
     *
     * @param examTime Date and time of the exam
     * @return List of exams
     */
    @Override
    public List<Exam> getByExamTime(Date examTime) {
        return null;
    }

}
