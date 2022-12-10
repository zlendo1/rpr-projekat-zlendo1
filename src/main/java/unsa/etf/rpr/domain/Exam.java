package unsa.etf.rpr.domain;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Bean for subscription
 */
public class Exam implements Serializable {

    private int examId;
    private Provider provider;
    private String courseName;
    private LocalDateTime examTime;
    private String answerSheet;

    public Exam() {
    }

    public Exam(int examId, Provider provider, String courseName, LocalDateTime examTime, String answerSheet) {
        this.examId = examId;
        this.provider = provider;
        this.courseName = courseName;
        this.examTime = examTime;
        this.answerSheet = answerSheet;
    }

    public int getExamId() {
        return examId;
    }

    public void setExamId(int examId) {
        this.examId = examId;
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

    public LocalDateTime getExamTime() {
        return examTime;
    }

    public void setExamTime(LocalDateTime examTime) {
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
        return getExamId() == exam.getExamId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getExamId());
    }

    @Override
    public String toString() {
        return "Exam{" +
                "examId=" + examId +
                ", provider=" + provider +
                ", courseName='" + courseName + '\'' +
                ", examTime=" + examTime +
                ", answerSheet='" + answerSheet + '\'' +
                '}';
    }

}
