package unsa.etf.rpr.dao;

import unsa.etf.rpr.connector.MyConnection;
import unsa.etf.rpr.domain.User;
import unsa.etf.rpr.exception.DBHandleException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * SQL implementation of UserDao
 *
 */
public class UserDaoSQLImpl implements UserDao {

    private final Connection connection;

    /**
     * Establishes connection to the DB
     *
     */
    public UserDaoSQLImpl() throws DBHandleException {
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
    public User getById(int id) throws DBHandleException {
        String query = "SELECT * FROM user WHERE user_id = ?";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                User user = new User(
                        resultSet.getInt("user_id"),
                        resultSet.getString("username"),
                        resultSet.getString("password"),
                        resultSet.getString("first_name"),
                        resultSet.getString("last_name")
                );

                resultSet.close();

                return user;
            }

        } catch (SQLException e) {
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
    public User add(User item) throws DBHandleException {
        String insert = "INSERT INTO user(first_name, last_name) VALUES(?, ?)";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setString(1, item.getFirstName());
            preparedStatement.setString(2, item.getLastName());

            preparedStatement.executeUpdate();

            ResultSet resultSet = preparedStatement.getGeneratedKeys();

            resultSet.next();

            item.setUserId(resultSet.getInt(1));

            resultSet.close();

            return item;

        } catch (SQLException e) {
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
    public User update(User item) throws DBHandleException {
        String update = "UPDATE user SET first_name = ?, last_name = ? WHERE user_id = ?";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(update);

            preparedStatement.setInt(3, item.getUserId());
            preparedStatement.setString(1, item.getFirstName());
            preparedStatement.setString(2, item.getLastName());

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
        String delete = "DELETE FROM user WHERE user_id = ?";

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
    public List<User> getAll() throws DBHandleException {
        List<User> userList = new ArrayList<>();

        String query = "SELECT * FROM user";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                userList.add(new User(
                        resultSet.getInt("user_id"),
                        resultSet.getString("username"),
                        resultSet.getString("password"),
                        resultSet.getString("first_name"),
                        resultSet.getString("last_name"))
                );
            }

            resultSet.close();

        } catch (SQLException e) {
            throw new DBHandleException(e);
        }

        return userList;
    }

    /**
     * Search users in DB based on their first names
     *
     * @param firstName First name of a user
     * @return List of users
     * @throws DBHandleException In case of any DB handling error
     */
    @Override
    public List<User> getByFirstName(String firstName) throws DBHandleException {
        List<User> userList = new ArrayList<>();

        String query = "SELECT * FROM user WHERE first_name = ?";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            preparedStatement.setString(1, firstName);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                userList.add(new User(
                        resultSet.getInt("user_id"),
                        resultSet.getString("username"),
                        resultSet.getString("password"),
                        resultSet.getString("first_name"),
                        resultSet.getString("last_name"))
                );
            }

            resultSet.close();

        } catch (SQLException e) {
            throw new DBHandleException(e);
        }

        return userList;
    }

    /**
     * Search users in DB based on their last name
     *
     * @param lastName Last name of a user
     * @return List of users
     * @throws DBHandleException In case of any DB handling error
     */
    @Override
    public List<User> getByLastName(String lastName) throws DBHandleException {
        List<User> userList = new ArrayList<>();

        String query = "SELECT * FROM user WHERE last_name = ?";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            preparedStatement.setString(1, lastName);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                userList.add(new User(
                        resultSet.getInt("user_id"),
                        resultSet.getString("username"),
                        resultSet.getString("password"),
                        resultSet.getString("first_name"),
                        resultSet.getString("last_name"))
                );
            }

            resultSet.close();

        } catch (SQLException e) {
            throw new DBHandleException(e);
        }

        return userList;
    }

    /**
     * Search users in DB based on their username
     *
     * @param username Username of a user
     * @return A user object
     * @throws DBHandleException In case of any DB handling error
     */
    @Override
    public User getByUsername(String username) throws DBHandleException {
        User user = null;

        String query = "SELECT * FROM user WHERE username = ?";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            preparedStatement.setString(1, username);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                user = new User(
                        resultSet.getInt("user_id"),
                        resultSet.getString("username"),
                        resultSet.getString("password"),
                        resultSet.getString("first_name"),
                        resultSet.getString("last_name")
                );
            }

            resultSet.close();

        } catch (SQLException e) {
            throw new DBHandleException(e);
        }

        return user;
    }

    /**
     * Search user in DB based on their password
     *
     * @param password Password of a user
     * @return List of users
     * @throws DBHandleException In case of any DB handling error
     */
    @Override
    public List<User> getByPassword(String password) throws DBHandleException {
        return null;
    }

}
