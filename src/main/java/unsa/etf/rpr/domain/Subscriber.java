package unsa.etf.rpr.domain;

import java.io.Serializable;
import java.util.Objects;

/**
 * Bean for subscriber
 */
public class Subscriber implements Serializable {

    private int subscriberId;
    private String preferences;

    public Subscriber() {
    }

    public Subscriber(int subscriberId, String preferences) {
        this.subscriberId = subscriberId;
        this.preferences = preferences;
    }

    public int getSubscriberId() {
        return subscriberId;
    }

    public void setSubscriberId(int subscriberId) {
        this.subscriberId = subscriberId;
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
        return getSubscriberId() == that.getSubscriberId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getSubscriberId());
    }

    @Override
    public String toString() {
        return "Subscriber{" +
                "subscriberId=" + subscriberId +
                ", preferences='" + preferences + '\'' +
                '}';
    }

}
