package unsa.etf.rpr.dao;

import unsa.etf.rpr.domain.Exam;
import unsa.etf.rpr.domain.Provider;

import java.util.Date;
import java.util.List;

/**
 * Dao interface for Exam bean
 */
public interface ExamDao extends Dao<Exam> {

    /**
     * Search for exams in DB by provider
     * @param provider The provider that provided the exams
     * @return List of exams
     */
    List<Exam> getByProvider(Provider provider);

    /**
     * Search for exams in DB by course name
     * @param courseName Name of the course the exam belongs to
     * @return List of exams
     */
    List<Exam> getByCourseName(String courseName);

    /**
     * Search for exams in DB by exam time
     * @param examTime Date and time of the exam
     * @return List of exams
     */
    List<Exam> getByExamTime(Date examTime);

}
