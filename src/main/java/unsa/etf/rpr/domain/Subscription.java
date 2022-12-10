package unsa.etf.rpr.domain;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Bean for subscription
 */
public class Subscription implements Serializable {

    private int subscriptionId;
    private Subscriber subscriber;
    private Exam exam;
    private LocalDateTime exporation;

    public Subscription() {
    }

    public Subscription(int subscriptionId, Subscriber subscriber, Exam exam, LocalDateTime exporation) {
        this.subscriptionId = subscriptionId;
        this.subscriber = subscriber;
        this.exam = exam;
        this.exporation = exporation;
    }

    public int getSubscriptionId() {
        return subscriptionId;
    }

    public void setSubscriptionId(int subscriptionId) {
        this.subscriptionId = subscriptionId;
    }

    public Subscriber getSubscriber() {
        return subscriber;
    }

    public void setSubscriber(Subscriber subscriber) {
        this.subscriber = subscriber;
    }

    public Exam getExam() {
        return exam;
    }

    public void setExam(Exam exam) {
        this.exam = exam;
    }

    public LocalDateTime getExporation() {
        return exporation;
    }

    public void setExporation(LocalDateTime exporation) {
        this.exporation = exporation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Subscription)) return false;
        Subscription that = (Subscription) o;
        return getSubscriptionId() == that.getSubscriptionId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getSubscriptionId());
    }

    @Override
    public String toString() {
        return "Subscription{" +
                "subscriptionId=" + subscriptionId +
                ", subscriber=" + subscriber +
                ", exam=" + exam +
                ", exporation=" + exporation +
                '}';
    }

}
