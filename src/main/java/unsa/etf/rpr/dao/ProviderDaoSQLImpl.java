package unsa.etf.rpr.dao;

import unsa.etf.rpr.domain.Provider;

import java.sql.Connection;
import java.time.LocalDateTime;
import java.util.List;

public class ProviderDaoSQLImpl implements ProviderDao {

    private Connection connection;

    public ProviderDaoSQLImpl() {
    }

    @Override
    public Provider getById(int id) {
        return null;
    }

    @Override
    public Provider add(Provider item) {
        return null;
    }

    @Override
    public Provider update(Provider item) {
        return null;
    }

    @Override
    public void delete(int id) {

    }

    @Override
    public List<Provider> getAll() {
        return null;
    }

    @Override
    public List<Provider> getByDateRange(LocalDateTime begin, LocalDateTime end) {
        return null;
    }

}
