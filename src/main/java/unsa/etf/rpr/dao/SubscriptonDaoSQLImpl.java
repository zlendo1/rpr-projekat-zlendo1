package unsa.etf.rpr.dao;

import unsa.etf.rpr.domain.Exam;
import unsa.etf.rpr.domain.Subscriber;
import unsa.etf.rpr.domain.Subscription;

import java.sql.Connection;
import java.util.List;

public class SubscriptonDaoSQLImpl implements SubscriptionDao {

    private Connection connection;

    /**
     * Establishes connection to the DB
     *
     */
    public SubscriptonDaoSQLImpl() {
    }

    /**
     * Get entity from database coresponding to its primary key
     *
     * @param id primary key of entity
     * @return corresponding entity
     */
    @Override
    public Subscription getById(int id) {
        return null;
    }

    /**
     * Saves entity into database
     *
     * @param item bean for saving into database
     * @return updated version of the bean
     */
    @Override
    public Subscription add(Subscription item) {
        return null;
    }

    /**
     * Updates entity from database based on its primary key
     *
     * @param item bean which we will update (id must be populated)
     * @return updated version of the bean
     */
    @Override
    public Subscription update(Subscription item) {
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
