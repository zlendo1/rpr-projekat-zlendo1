package ba.unsa.etf.rpr.domain;

import javafx.beans.property.StringProperty;
import javafx.beans.property.SimpleStringProperty;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/**
 * Bean for subscription
 *
 */
public class Exam implements Serializable, Idable {

    private int id;
    private User user;
    private Course course;
    private Date examTime;
    private String answerSheet;

    public Exam() {
    }

    public Exam(int id, User user, Course course, Date examTime, String answerSheet) {
        this.id = id;
        this.user = user;
        this.course = course;
        this.examTime = examTime;
        this.answerSheet = answerSheet;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Date getExamTime() {
        return examTime;
    }

    public void setExamTime(Date examTime) {
        this.examTime = examTime;
    }

    public String getAnswerSheet() {
        return answerSheet;
    }

    public void setAnswerSheet(String answerSheet) {
        this.answerSheet = answerSheet;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Exam)) return false;
        Exam exam = (Exam) o;
        return getId() == exam.getId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    @Override
    public String toString() {
        return "Exam{" +
                "id=" + id +
                ", user=" + user +
                ", course=" + course +
                ", examTime=" + examTime +
                ", answerSheet='" + answerSheet + '\'' +
                '}';
    }

    public String getUsername() {
        return user.getUsername();
    }

    public String getCourseName() {
        return course.getName();
    }

    public String getProfessor() {
        return course.getProfessor();
    }

}
