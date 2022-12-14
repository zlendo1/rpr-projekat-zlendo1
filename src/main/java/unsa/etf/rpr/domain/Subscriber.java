package unsa.etf.rpr.domain;

import java.io.Serializable;
import java.util.Objects;

/**
 * Bean for subscriber
 */
public class Subscriber implements Serializable {

    private User user;
    private String preferences;

    public Subscriber() {
    }

    public Subscriber(User user, String preferences) {
        this.user = user;
        this.preferences = preferences;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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
        return Objects.equals(getUser(), that.getUser());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUser());
    }

    @Override
    public String toString() {
        return "Subscriber{" +
                "user=" + user +
                ", preferences='" + preferences + '\'' +
                '}';
    }

}
