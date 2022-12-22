package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Course;
import ba.unsa.etf.rpr.domain.User;
import ba.unsa.etf.rpr.exception.DBHandleException;
import ba.unsa.etf.rpr.domain.Exam;

import java.util.Date;
import java.util.List;

/**
 * Dao interface for Exam bean
 *
 */
public interface ExamDao extends Dao<Exam> {

    /**
     * Search for exams in DB by user
     *
     * @param user The user that provided the exams
     * @return List of exams
     * @throws DBHandleException In case of any DB handling error
     */
    List<Exam> getByUser(User user) throws DBHandleException;

    /**
     * Search for exams in DB by course
     *
     * @param course The course the exam belongs to
     * @return List of exams
     * @throws DBHandleException In case of any DB handling error
     */
    List<Exam> getByCourse(Course course) throws DBHandleException;

    /**
     * Search for exams in DB by exam time
     *
     * @param examTime Date and time of the exam
     * @return List of exams
     * @throws DBHandleException In case of any DB handling error
     */
    List<Exam> getByExamTime(Date examTime) throws DBHandleException;

}
