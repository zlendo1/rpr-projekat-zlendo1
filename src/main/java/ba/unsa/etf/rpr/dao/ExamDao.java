package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.exception.DBHandleException;
import ba.unsa.etf.rpr.domain.Exam;
import ba.unsa.etf.rpr.domain.Provider;

import java.util.Date;
import java.util.List;

/**
 * Dao interface for Exam bean
 *
 */
public interface ExamDao extends Dao<Exam> {

    /**
     * Search for exams in DB by provider
     *
     * @param provider The provider that provided the exams
     * @return List of exams
     * @throws DBHandleException In case of any DB handling error
     */
    List<Exam> getByProvider(Provider provider) throws DBHandleException;

    /**
     * Search for exams in DB by course name
     *
     * @param courseName Name of the course the exam belongs to
     * @return List of exams
     * @throws DBHandleException In case of any DB handling error
     */
    List<Exam> getByCourseName(String courseName) throws DBHandleException;

    /**
     * Search for exams in DB by exam time
     *
     * @param examTime Date and time of the exam
     * @return List of exams
     * @throws DBHandleException In case of any DB handling error
     */
    List<Exam> getByExamTime(Date examTime) throws DBHandleException;

}
