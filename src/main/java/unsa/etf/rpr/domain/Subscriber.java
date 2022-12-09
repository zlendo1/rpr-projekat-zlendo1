package unsa.etf.rpr.domain;

import java.io.Serializable;

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

}
