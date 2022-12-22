package ba.unsa.etf.rpr.domain;

import java.util.Objects;

/**
 * Bean for course
 *
 */
public class Course implements Idable{
    private int id;
    private String name;
    private String professor;

    public Course() {
    }

    public Course(int id, String name, String professor) {
        this.id = id;
        this.name = name;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
