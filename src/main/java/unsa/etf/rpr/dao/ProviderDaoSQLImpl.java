package unsa.etf.rpr.dao;

import unsa.etf.rpr.connector.MyConnection;
import unsa.etf.rpr.domain.Provider;
import unsa.etf.rpr.exception.DBHandleException;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.List;

public class ProviderDaoSQLImpl implements ProviderDao {

    private final Connection connection;

    /**
     * Establishes connection to the DB
     *
     */
    public ProviderDaoSQLImpl() throws DBHandleException {
        connection = MyConnection.EstablishConnection();
    }


    /**
     * Get entity from database coresponding to its primary key
     *
     * @param id primary key of entity
     * @return corresponding entity
     */
    @Override
    public Provider getById(int id) throws DBHandleException {
        String query = "SELECT * FROM provider WHERE provider_id = ?";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                Provider provider = new Provider(
                        new PersonDaoSQLImpl().getById(resultSet.getInt("provider_id")),
                        resultSet.getTimestamp("contract_start"),
                        resultSet.getTimestamp("contract_expiry")
                );

                resultSet.close();

                return provider;
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
    public Provider add(Provider item) {
        return null;
    }

    /**
     * Updates entity from database based on its primary key
     *
     * @param item bean which we will update (id must be populated)
     * @return updated version of the bean
     */
    @Override
    public Provider update(Provider item) throws DBHandleException {
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
    public List<Provider> getAll() {
        return null;
    }

    /**
     * Search providers in DB based on their contract validity in a time period
     *
     * @param begin Starting datetime (inclusive)
     * @param end   Ending datetime (inclusive)
     * @return List of providers
     */
    @Override
    public List<Provider> getByDateRange(LocalDateTime begin, LocalDateTime end) {
        return null;
    }

}
