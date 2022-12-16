package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.connector.MyConnection;
import ba.unsa.etf.rpr.domain.Exam;
import ba.unsa.etf.rpr.domain.Subscriber;
import ba.unsa.etf.rpr.domain.Subscription;
import ba.unsa.etf.rpr.exception.DBHandleException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * SQL implementation of SubscriptionDao
 *
 */
public class SubscriptonDaoSQLImpl implements SubscriptionDao {

    private final Connection connection;

    /**
     * Establishes connection to the DB
     *
     */
    public SubscriptonDaoSQLImpl() throws DBHandleException {
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
     * @throws DBHandleException In case of any DB handling error
     */
    @Override
    public Subscription add(Subscription item) throws DBHandleException {
        String insert = "INSERT INTO subscriber(subscriber_id, exam_id, exporation) VALUES(?, ?, ?)";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setInt(1, item.getSubscriber().getUser().getUserId());
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
     * @throws DBHandleException In case of any DB handling error
     */
    @Override
    public Subscription update(Subscription item) throws DBHandleException {
        String update = "UPDATE subscription SET subscriber_id = ?, exam_id = ?, exporation = ? WHERE subscription_id = ?";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(update);

            preparedStatement.setInt(4, item.getSubscriptionId());
            preparedStatement.setInt(1, item.getSubscriber().getUser().getUserId());
            preparedStatement.setInt(2, item.getExam().getExamId());
            preparedStatement.setTimestamp(3, (Timestamp) item.getExporation());

            preparedStatement.executeUpdate();

            preparedStatement.close();

            return item;

        } catch (SQLException e) {
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
        String delete = "DELETE FROM subscription WHERE subscription_id = ?";

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
    public List<Subscription> getAll() throws DBHandleException {
        List<Subscription> subscriptionList = new ArrayList<>();

        String query = "SELECT * FROM subscription";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                subscriptionList.add(new Subscription(
                        resultSet.getInt("subscription_id"),
                        new SubscriberDaoSQLImpl().getById(resultSet.getInt("subscriber_id")),
                        new ExamDaoSQLImpl().getById(resultSet.getInt("exam_id")),
                        resultSet.getTimestamp("exporation")
                ));
            }

            resultSet.close();

        } catch (Exception e) {
            throw new DBHandleException(e);
        }

        return subscriptionList;
    }

    /**
     * Search subscriptions in DB by subscriber
     *
     * @param subscriber The subscriber the subscription belongs to
     * @return List of subscriptions
     * @throws DBHandleException In case of any DB handling error
     */
    @Override
    public List<Subscription> getBySubscriber(Subscriber subscriber) throws DBHandleException {
        List<Subscription> subscriptionList = new ArrayList<>();

        String query = "SELECT * FROM subscription WHERE subscriber_id = ?";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            preparedStatement.setInt(1, subscriber.getUser().getUserId());

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                subscriptionList.add(new Subscription(
                        resultSet.getInt("subscription_id"),
                        new SubscriberDaoSQLImpl().getById(resultSet.getInt("subscriber_id")),
                        new ExamDaoSQLImpl().getById(resultSet.getInt("exam_id")),
                        resultSet.getTimestamp("exporation")
                ));
            }

            resultSet.close();

        } catch (Exception e) {
            throw new DBHandleException(e);
        }

        return subscriptionList;
    }

    /**
     * Search subscriptions in DB by exam
     *
     * @param exam The exam the subscription belongs to
     * @return List of subscriptions
     * @throws DBHandleException In case of any DB handling error
     */
    @Override
    public List<Subscription> getByExam(Exam exam) throws DBHandleException {
        List<Subscription> subscriptionList = new ArrayList<>();

        String query = "SELECT * FROM subscription WHERE exam_id = ?";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            preparedStatement.setInt(1, exam.getExamId());

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                subscriptionList.add(new Subscription(
                        resultSet.getInt("subscription_id"),
                        new SubscriberDaoSQLImpl().getById(resultSet.getInt("subscriber_id")),
                        new ExamDaoSQLImpl().getById(resultSet.getInt("exam_id")),
                        resultSet.getTimestamp("exporation")
                ));
            }

            resultSet.close();

        } catch (Exception e) {
            throw new DBHandleException(e);
        }

        return subscriptionList;
    }

    /**
     * Search subscriotion in DB that have expired
     *
     * @return List of subscriptions
     * @throws DBHandleException In case of any DB handling error
     */
    @Override
    public List<Subscription> getExpired() throws DBHandleException {
        List<Subscription> subscriptionList = new ArrayList<>();

        String query = "SELECT * FROM subscription WHERE exporation <= (SELECT sysdate FROM dual)";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                subscriptionList.add(new Subscription(
                        resultSet.getInt("subscription_id"),
                        new SubscriberDaoSQLImpl().getById(resultSet.getInt("subscriber_id")),
                        new ExamDaoSQLImpl().getById(resultSet.getInt("exam_id")),
                        resultSet.getTimestamp("exporation")
                ));
            }

            resultSet.close();

        } catch (Exception e) {
            throw new DBHandleException(e);
        }

        return subscriptionList;
    }

    /**
     * Search subscriptions in DB that have not expired yet
     *
     * @return List of subscriptions
     * @throws DBHandleException In case of any DB handling error
     */
    @Override
    public List<Subscription> getUnexpired() throws DBHandleException {
        List<Subscription> subscriptionList = new ArrayList<>();

        String query = "SELECT * FROM subscription WHERE exporation > (SELECT sysdate FROM dual)";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                subscriptionList.add(new Subscription(
                        resultSet.getInt("subscription_id"),
                        new SubscriberDaoSQLImpl().getById(resultSet.getInt("subscriber_id")),
                        new ExamDaoSQLImpl().getById(resultSet.getInt("exam_id")),
                        resultSet.getTimestamp("exporation")
                ));
            }

            resultSet.close();

        } catch (Exception e) {
            throw new DBHandleException(e);
        }

        return subscriptionList;
    }

}
