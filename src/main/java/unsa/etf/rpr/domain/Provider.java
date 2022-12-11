package unsa.etf.rpr.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/**
 * Bean for provider
 */
public class Provider implements Serializable {

    private Person person;
    private Date contractStart;
    private Date contractExpiry;

    public Provider() {
    }

    public Provider(Person person, Date contractStart, Date contractExpiry) {
        this.person = person;
        this.contractStart = contractStart;
        this.contractExpiry = contractExpiry;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
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
        return Objects.equals(getPerson(), provider.getPerson());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPerson());
    }

    @Override
    public String toString() {
        return "Provider{" +
                "person=" + person +
                ", contractStart=" + contractStart +
                ", contractExpiry=" + contractExpiry +
                '}';
    }
}
