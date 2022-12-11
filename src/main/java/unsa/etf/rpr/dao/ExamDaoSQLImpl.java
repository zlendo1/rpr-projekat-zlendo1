package unsa.etf.rpr.dao;

import unsa.etf.rpr.connector.MyConnection;
import unsa.etf.rpr.domain.Exam;
import unsa.etf.rpr.domain.Provider;
import unsa.etf.rpr.exception.DBHandleException;

import java.sql.Connection;
import java.time.LocalDateTime;
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
    public Exam getById(int id) {
        return null;
    }

    /**
     * Saves entity into database
     *
     * @param item bean for saving into database
     * @return updated version of the bean
     */
    @Override
    public Exam add(Exam item) {
        return null;
    }

    /**
     * Updates entity from database based on its primary key
     *
     * @param item bean which we will update (id must be populated)
     * @return updated version of the bean
     */
    @Override
    public Exam update(Exam item) throws DBHandleException {
        return null;
    }

    /**
     * Hard delete of entity with the corseponding primary key
     *
     * @param id primary key of the entity
     */
    @Override
    public void delete(int id) {

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
    public List<Exam> getByExamTime(LocalDateTime examTime) {
        return null;
    }

}
