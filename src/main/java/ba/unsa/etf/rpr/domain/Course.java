package ba.unsa.etf.rpr.domain;

import java.util.Objects;

public class Course implements Idable{
    private int id;
    private String professor;

    public Course() {
    }

    public Course(int id, String professor) {
        this.id = id;
        this.professor = professor;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    public String getProfessor() {
        return professor;
    }

    public void setProfessor(String professor) {
        this.professor = professor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Course)) return false;
        Course course = (Course) o;
        return getId() == course.getId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", professor='" + professor + '\'' +
                '}';
    }

}
