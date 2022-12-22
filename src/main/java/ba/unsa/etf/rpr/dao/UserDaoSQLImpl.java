package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.User;
import ba.unsa.etf.rpr.exception.DBHandleException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * SQL implementation of UserDao
 *
 */
public class UserDaoSQLImpl extends AbstractDao<User> implements UserDao {

    public UserDaoSQLImpl() throws DBHandleException {
        super("user");
    }

    /**
     * Extracts a bean from a ResultSet object.
     *
     * @param resultSet Contains the row we want to extract
     * @return Bean object
     * @throws DBHandleException In case of errors while working with ResultSet
     */
    @Override
    public User rowToObject(ResultSet resultSet) throws DBHandleException {
        try {
            return new User(
                    resultSet.getInt("user_id"),
                    resultSet.getString("username"),
                    resultSet.getString("password"),
                    resultSet.getString("first_name"),
                    resultSet.getString("last_name")
                );

        } catch (SQLException e) {
            throw new DBHandleException(e);
        }
    }

    /**
     * Get row utilising a Map object from a bean object.
     * The keys are the names of the bean's attributes.
     *
     * @param object Bean object which we want converted
     * @return Map object containing bean
     */
    @Override
    public Map<String, Object> objectToRow(User object) {
        return null;
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
            PreparedStatement preparedStatement = getConnection().prepareStatement(query);

            preparedStatement.setString(1, firstName);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                userList.add(new User(
                        resultSet.getInt("user_id"),
                        resultSet.getString("username"),
                        resultSet.getString("password"),
                        resultSet.getString("first_name"),
                        resultSet.getString("last_name")
                        )
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
            PreparedStatement preparedStatement = getConnection().prepareStatement(query);

            preparedStatement.setString(1, lastName);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                userList.add(new User(
                        resultSet.getInt("user_id"),
                        resultSet.getString("username"),
                        resultSet.getString("password"),
                        resultSet.getString("first_name"),
                        resultSet.getString("last_name")
                        )
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
            PreparedStatement preparedStatement = getConnection().prepareStatement(query);

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
        List<User> userList = new ArrayList<>();

        String query = "SELECT * FROM user WHERE password = ?";

        try {
            PreparedStatement preparedStatement = getConnection().prepareStatement(query);

            preparedStatement.setString(1, password);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                userList.add(new User(
                        resultSet.getInt("user_id"),
                        resultSet.getString("username"),
                        resultSet.getString("password"),
                        resultSet.getString("first_name"),
                        resultSet.getString("last_name")
                        )
                );
            }

            resultSet.close();

        } catch (SQLException e) {
            throw new DBHandleException(e);
        }

        return userList;
    }

}
