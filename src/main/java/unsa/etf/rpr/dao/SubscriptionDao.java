package unsa.etf.rpr.dao;

import unsa.etf.rpr.domain.Exam;
import unsa.etf.rpr.domain.Subscriber;
import unsa.etf.rpr.domain.Subscription;
import unsa.etf.rpr.exception.DBHandleException;

import java.util.List;

/**
 * Dao interface for Subscription bean
 */
public interface SubscriptionDao extends Dao<Subscription> {

    /**
     * Search subscriptions in DB by subscriber
     * @param subscriber The subscriber the subscription belongs to
     * @return List of subscriptions
     */
    List<Subscription> getBySubscriber(Subscriber subscriber) throws DBHandleException;

    /**
     * Search subscriptions in DB by exam
     * @param exam The exam the subscription belongs to
     * @return List of subscriptions
     */
    List<Subscription> getByExam(Exam exam) throws DBHandleException;

    /**
     * Search subscriotion in DB that have expired
     * @return List of subscriptions
     */
    List<Subscription> getExpired() throws DBHandleException;

    /**
     * Search subscriptions in DB that have not expired yet
     * @return List of subscriptions
     */
    List<Subscription> getUnexpired() throws DBHandleException;

}
