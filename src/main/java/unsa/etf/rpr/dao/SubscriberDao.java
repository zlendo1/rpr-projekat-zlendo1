package unsa.etf.rpr.dao;

import unsa.etf.rpr.domain.Subscriber;

import java.util.List;

/**
 * Dao interface for Subscriber bean
 */
public interface SubscriberDao extends Dao<Subscriber> {

    /**
     * Search subscribers in DB by their exam preferences
     * @param preference Exam preference (only one)
     * @return List of subscribers
     */
    List<Subscriber> getByPreference(String preference);
}
