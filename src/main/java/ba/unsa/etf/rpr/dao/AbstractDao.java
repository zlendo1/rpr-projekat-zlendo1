package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.connector.MyConnection;
import ba.unsa.etf.rpr.domain.Exam;
import ba.unsa.etf.rpr.domain.Idable;
import ba.unsa.etf.rpr.exception.DBHandleException;

import java.sql.*;
import java.util.AbstractMap;
import java.util.List;
import java.util.Map;

/**
 * Abstract DAO class which will be extended by the appropriate bean and DB implementation.
 *
 * @param <T> Bean class for which we are implementing the DAO
 */
public abstract class AbstractDao <T extends Idable> implements Dao<T> {
    private final Connection connection;
    private final String tableName;

    public AbstractDao(String tableName) throws DBHandleException {
        this.tableName = tableName;
        this.connection = MyConnection.getInstance().getConnection();
    }

    /**
     * Converts a ResultSet object containing a single row inside
     * into the appropriate bean.
     *
     * @param resultSet Contains the row we want to extract
     * @return Bean object
     * @throws DBHandleException In case of errors while working with ResultSet
     */
    public abstract T rowToObject(ResultSet resultSet) throws DBHandleException;

    /**
     * Get row utilising a Map object from a bean object.
     * The keys are the names of the bean's attributes.
     *
     * @param object Bean object which we want converted
     * @return Map object containing bean
     */
    public abstract Map<String, Object> objectToRow(T object);

    /**
     * Get entity from database coresponding to its primary key.
     *
     * @param id primary key of entity
     * @return corresponding entity
     * @throws DBHandleException In case of any DB handling error
     */
    @Override
    public T getById(int id) throws DBHandleException {
        String query = "SELECT * FROM " + this.tableName + " WHERE id = ?";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                T result = rowToObject(resultSet);

                resultSet.close();

                return result;
            } else {
                throw new DBHandleException("Object not found");
            }

        } catch (SQLException e) {
            throw new DBHandleException(e);
        }
    }

    /**
     * Saves entity into database.
     *
     * @param item bean for saving into database
     * @return updated version of the bean
     * @throws DBHandleException In case of any DB handling error
     */
    @Override
    public T add(T item) throws DBHandleException {
        Map<String, Object> row = objectToRow(item);
        Map.Entry<String, String> columns = prepareInsertParts(row);

        StringBuilder builder = new StringBuilder();

        builder.append("INSERT INTO ")
                .append(tableName)
                .append(" (").append(columns.getKey()).append(") ")
                .append("VALUES (").append(columns.getValue()).append(")");

        try {
            PreparedStatement preparedStatement =
                    connection.prepareStatement(builder.toString(),Statement.RETURN_GENERATED_KEYS);

            int i = 1;

            for (Map.Entry<String, Object> entry: row.entrySet()) {
                if (entry.getKey().equals("id")) {
                    continue;
                }

                preparedStatement.setObject(i++, entry.getValue());
            }

            preparedStatement.executeUpdate();

            ResultSet resultSet = preparedStatement.getGeneratedKeys();

            resultSet.next();

            item.setId(resultSet.getInt(1));

            resultSet.close();

            return item;

        } catch (Exception e) {
            throw new DBHandleException(e);
        }
    }

    /**
     * Updates entity from database based on its primary key.
     *
     * @param item bean which we will update (id must be populated)
     * @return updated version of the bean
     * @throws DBHandleException In case of any DB handling error
     */
    @Override
    public T update(T item) throws DBHandleException {
        Map<String, Object> row = objectToRow(item);
        String updateColumns = prepareUpdateParts(row);

        StringBuilder builder = new StringBuilder();

        builder.append("UPDATE ")
                .append(tableName)
                .append(" SET ")
                .append(updateColumns)
                .append(" WHERE id = ?");

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(builder.toString());

            int i = 1;

            for (Map.Entry<String, Object> entry: row.entrySet()) {
                if (entry.getKey().equals("id")) {
                    continue;
                }

                preparedStatement.setObject(i++, entry.getValue());
            }

            preparedStatement.setObject(i, item.getId());

            preparedStatement.executeUpdate();

            preparedStatement.close();

            return item;

        } catch (Exception e) {
            throw new DBHandleException(e);
        }
    }

    /**
     * Hard delete of entity with the corseponding primary key.
     *
     * @param id primary key of the entity
     * @throws DBHandleException In case of any DB handling error
     */
    @Override
    public void delete(int id) throws DBHandleException {
        String delete = "DELETE FROM " + tableName +" WHERE id = ?";

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
     * Lists all entites from the database.
     *
     * @return list of entities from the database
     * @throws DBHandleException In case of any DB handling error
     */
    @Override
    public List<T> getAll() throws DBHandleException {
        return null;
    }

    /**
     * Prepares a CSV of columns and question marks for insertion statement
     *
     * @param row Map containing column names as keys
     * @return CSV of columns and question marks
     */
    private Map.Entry<String, String> prepareInsertParts(Map<String, Object> row) {
        StringBuilder columns = new StringBuilder();
        StringBuilder questions = new StringBuilder();

        int i = 0;

        for (Map.Entry<String, Object> entry : row.entrySet()) {
            ++i;

            String column = entry.getKey();

            if (column.equals("id")) {
                continue;
            }

            columns.append(column);
            questions.append("?");

            if (i != row.size()) {
                columns.append(",");
                questions.append(",");
            }
        }

        return new AbstractMap.SimpleEntry<String, String>(columns.toString(), questions.toString());
    }

    /**
     * Prepare columns for update statement
     *
     * @param row Map containing column names as key values
     * @return String of columns for preparation
     */
    private String prepareUpdateParts(Map<String, Object> row) {
        StringBuilder columns = new StringBuilder();

        int i = 0;

        for (Map.Entry<String, Object> entry : row.entrySet()) {
            ++i;

            String column = entry.getKey();

            if (column.equals("id")) {
                continue;
            }

            columns.append(column).append(" = ?");

            if (i != row.size()) {
                columns.append(",");
            }
        }

        return columns.toString();
    }

}
