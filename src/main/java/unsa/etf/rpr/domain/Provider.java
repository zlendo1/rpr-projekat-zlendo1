package unsa.etf.rpr.domain;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Bean for provider
 */
public class Provider implements Serializable {

    private Person person;
    private LocalDateTime contractStart;
    private LocalDateTime contractExpiry;

    public Provider() {
    }

    public Provider(Person person, LocalDateTime contractStart, LocalDateTime contractExpiry) {
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

    public LocalDateTime getContractStart() {
        return contractStart;
    }

    public void setContractStart(LocalDateTime contractStart) {
        this.contractStart = contractStart;
    }

    public LocalDateTime getContractExpiry() {
        return contractExpiry;
    }

    public void setContractExpiry(LocalDateTime contractExpiry) {
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
