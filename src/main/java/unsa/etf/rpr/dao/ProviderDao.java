package unsa.etf.rpr.dao;

import unsa.etf.rpr.domain.Provider;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Dao interface for Provider bean
 */
public interface ProviderDao extends Dao<Provider> {

    /**
     * Search providers in DB based on their contract validity in a time period
     * @param begin Starting datetime (inclusive)
     * @param end Ending datetime (inclusive)
     * @return List of providers
     */
    List<Provider> getByDateRange(LocalDateTime begin, LocalDateTime end);

}
