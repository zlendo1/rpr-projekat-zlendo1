package unsa.etf.rpr.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/**
 * Bean for subscription
 *
 */
public class Subscription implements Serializable {

    private int subscriptionId;
    private Subscriber subscriber;
    private Exam exam;
    private Date exporation;

    public Subscription() {
    }

    public Subscription(int subscriptionId, Subscriber subscriber, Exam exam, Date exporation) {
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

    public Date getExporation() {
        return exporation;
    }

    public void setExporation(Date exporation) {
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
