package ba.unsa.etf.rpr.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/**
 * Bean for provider
 *
 */
public class Provider implements Serializable {

    private User user;
    private Date contractStart;
    private Date contractExpiry;

    public Provider() {
    }

    public Provider(User user, Date contractStart, Date contractExpiry) {
        this.user = user;
        this.contractStart = contractStart;
        this.contractExpiry = contractExpiry;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getContractStart() {
        return contractStart;
    }

    public void setContractStart(Date contractStart) {
        this.contractStart = contractStart;
    }

    public Date getContractExpiry() {
        return contractExpiry;
    }

    public void setContractExpiry(Date contractExpiry) {
        this.contractExpiry = contractExpiry;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Provider)) return false;
        Provider provider = (Provider) o;
        return Objects.equals(getUser(), provider.getUser());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUser());
    }

    @Override
    public String toString() {
        return "Provider{" +
                "user=" + user +
                ", contractStart=" + contractStart +
                ", contractExpiry=" + contractExpiry +
                '}';
    }

}
