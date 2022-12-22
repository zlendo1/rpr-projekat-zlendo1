package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.exception.DBHandleException;

/**
 * Singleton factory for Dao objects
 *
 */
public class DaoFactory {

    private static UserDao userDao = null;
    private static CourseDao courseDao = null;
    private static ExamDao examDao = null;

    private DaoFactory() {}

    public static UserDao userDao() throws DBHandleException {
        if (userDao == null) {
            userDao = new UserDaoSQLImpl();
        }

        return userDao;
    }

    public static CourseDao courseDao() throws DBHandleException {
        if (courseDao == null) {
            courseDao = new CourseDaoSQLImpl();
        }

        return courseDao;
    }

    public static ExamDao examDao() throws DBHandleException {
        if (examDao == null) {
            examDao = new ExamDaoSQLImpl();
        }

        return examDao;
    }

}