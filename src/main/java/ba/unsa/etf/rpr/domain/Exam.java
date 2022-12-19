package ba.unsa.etf.rpr.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/**
 * Bean for subscription
 *
 */
public class Exam implements Serializable, Idable {

    private int id;
    private Provider provider;
    private String courseName;
    private Date examTime;
    private String answerSheet;

    public Exam() {
    }

    public Exam(int id, Provider provider, String courseName, Date examTime, String answerSheet) {
        this.id = id;
        this.provider = provider;
        this.courseName = courseName;
        this.examTime = examTime;
        this.answerSheet = answerSheet;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Provider getProvider() {
        return provider;
    }

    public void setProvider(Provider provider) {
        this.provider = provider;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
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
                ", provider=" + provider +
                ", courseName='" + courseName + '\'' +
                ", examTime=" + examTime +
                ", answerSheet='" + answerSheet + '\'' +
                '}';
    }

}
