package unsa.etf.rpr.domain;

import java.io.Serializable;
import java.util.Objects;

/**
 * Bean for subscriber
 */
public class Subscriber implements Serializable {

    private Person person;
    private String preferences;

    public Subscriber() {
    }

    public Subscriber(Person person, String preferences) {
        this.person = person;
        this.preferences = preferences;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public String getPreferences() {
        return preferences;
    }

    public void setPreferences(String preferences) {
        this.preferences = preferences;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Subscriber)) return false;
        Subscriber that = (Subscriber) o;
        return getPerson().equals(that.getPerson());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPerson());
    }

    @Override
    public String toString() {
        return "Subscriber{" +
                "person=" + person +
                ", preferences='" + preferences + '\'' +
                '}';
    }
}
