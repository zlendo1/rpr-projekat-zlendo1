package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.connector.MyConnection;
import ba.unsa.etf.rpr.domain.Provider;
import ba.unsa.etf.rpr.exception.DBHandleException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * SQL implementation of ProviderDao
 *
 */
public class ProviderDaoSQLImpl implements ProviderDao {

    private final Connection connection;

    /**
     * Establishes connection to the DB
     *
     */
    public ProviderDaoSQLImpl() throws DBHandleException {
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
    public Provider getById(int id) throws DBHandleException {
        String query = "SELECT * FROM provider WHERE provider_id = ?";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                Provider provider = new Provider(
                        new UserDaoSQLImpl().getById(resultSet.getInt("provider_id")),
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
     * @throws DBHandleException In case of any DB handling error
     */
    @Override
    public Provider add(Provider item) throws DBHandleException {
        String insert = "INSERT INTO provider(contract_start, contract_expiry) VALUES(?, ?)";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setTimestamp(1, (Timestamp) item.getContractStart());
            preparedStatement.setTimestamp(2, (Timestamp) item.getContractExpiry());

            preparedStatement.executeUpdate();

            ResultSet resultSet = preparedStatement.getGeneratedKeys();

            resultSet.next();

            item.setUser(new UserDaoSQLImpl().getById(resultSet.getInt(1)));

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
    public Provider update(Provider item) throws DBHandleException {
        String update = "UPDATE provider SET contract_start = ?, contract_expiry = ? WHERE provider_id = ?";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(update);

            preparedStatement.setInt(3, item.getUser().getUserId());
            preparedStatement.setTimestamp(1, (Timestamp) item.getContractStart());
            preparedStatement.setTimestamp(2, (Timestamp) item.getContractExpiry());

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
        String delete = "DELETE FROM provider WHERE provider_id = ?";

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
    public List<Provider> getAll() throws DBHandleException {
        List<Provider> providerList = new ArrayList<>();

        String query = "SELECT * FROM provider";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                providerList.add(new Provider(
                        new UserDaoSQLImpl().getById(resultSet.getInt("provider_id")),
                        resultSet.getTimestamp("contract_start"),
                        resultSet.getTimestamp("contract_expiry")
                ));
            }

            resultSet.close();

        } catch (Exception e) {
            throw new DBHandleException(e);
        }

        return providerList;
    }

    /**
     * Search providers in DB based on their contract validity in a time period
     *
     * @param begin Starting datetime (inclusive)
     * @param end   Ending datetime (inclusive)
     * @return List of providers
     * @throws DBHandleException In case of any DB handling error
     */
    @Override
    public List<Provider> getByDateRange(java.util.Date begin, java.util.Date end) throws DBHandleException {
        List<Provider> providerList = new ArrayList<>();

        String query = "SELECT * FROM provider WHERE contract_start >= ? AND contract_expiry <= ?";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            preparedStatement.setTimestamp(1, (Timestamp) begin);
            preparedStatement.setTimestamp(2, (Timestamp) end);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                providerList.add(new Provider(
                        new UserDaoSQLImpl().getById(resultSet.getInt("provider_id")),
                        resultSet.getTimestamp("contract_start"),
                        resultSet.getTimestamp("contract_expiry")
                ));
            }

            resultSet.close();

        } catch (Exception e) {
            throw new DBHandleException(e);
        }

        return providerList;
    }

}