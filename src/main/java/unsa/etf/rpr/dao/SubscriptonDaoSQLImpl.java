package unsa.etf.rpr.dao;

import unsa.etf.rpr.connector.MyConnection;
import unsa.etf.rpr.domain.Exam;
import unsa.etf.rpr.domain.Subscriber;
import unsa.etf.rpr.domain.Subscription;
import unsa.etf.rpr.exception.DBHandleException;

import java.sql.*;
import java.util.List;

public class SubscriptonDaoSQLImpl implements SubscriptionDao {

    private final Connection connection;

    /**
     * Establishes connection to the DB
     *
     */
    public SubscriptonDaoSQLImpl() throws DBHandleException {
        connection = MyConnection.EstablishConnection();
    }

    /**
     * Get entity from database coresponding to its primary key
     *
     * @param id primary key of entity
     * @return corresponding entity
     */
    @Override
    public Subscription getById(int id) throws DBHandleException {
        String query = "SELECT * FROM subscription WHERE subscription_id = ?";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                Subscription subscriber = new Subscription(
                        resultSet.getInt("subscription_id"),
                        new SubscriberDaoSQLImpl().getById(resultSet.getInt("subscriber_id")),
                        new ExamDaoSQLImpl().getById(resultSet.getInt("exam_id")),
                        resultSet.getTimestamp("exporation")
                );

                resultSet.close();

                return subscriber;
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
    public Subscription add(Subscription item) throws DBHandleException {
        String insert = "INSERT INTO subscriber(subscriber_id, exam_id, exporation) VALUES(?, ?, ?)";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setInt(1, item.getSubscriber().getPerson().getPersonId());
            preparedStatement.setInt(2, item.getExam().getExamId());
            preparedStatement.setTimestamp(3, (Timestamp) item.getExporation());

            preparedStatement.executeUpdate();

            ResultSet resultSet = preparedStatement.getGeneratedKeys();

            resultSet.next();

            item.setSubscriptionId(resultSet.getInt(1));

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
    public Subscription update(Subscription item) throws DBHandleException {
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
    public List<Subscription> getAll() {
        return null;
    }

    /**
     * Search subscriptions in DB by subscriber
     *
     * @param subscriber The subscriber the subscription belongs to
     * @return List of subscriptions
     */
    @Override
    public List<Subscription> getBySubscriber(Subscriber subscriber) {
        return null;
    }

    /**
     * Search subscriptions in DB by exam
     *
     * @param exam The exam the subscription belongs to
     * @return List of subscriptions
     */
    @Override
    public List<Subscription> getByExam(Exam exam) {
        return null;
    }

    /**
     * Search subscriotion in DB that have expired
     *
     * @return List of subscriptions
     */
    @Override
    public List<Subscription> getExpired() {
        return null;
    }

    /**
     * Search subscriptions in DB that have not expired yet
     *
     * @return List of subscriptions
     */
    @Override
    public List<Subscription> getUnexpired() {
        return null;
    }

}
