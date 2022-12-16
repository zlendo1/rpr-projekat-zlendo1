package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Subscriber;
import ba.unsa.etf.rpr.exception.DBHandleException;

import java.util.List;

/**
 * Dao interface for Subscriber bean
 *
 */
public interface SubscriberDao extends Dao<Subscriber> {

    /**
     * Search subscribers in DB by their exam preferences
     *
     * @param preference Exam preference (only one)
     * @return List of subscribers
     * @throws DBHandleException In case of any DB handling error
     */
    List<Subscriber> getByPreference(String preference) throws DBHandleException;
}
