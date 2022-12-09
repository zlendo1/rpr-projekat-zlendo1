package unsa.etf.rpr.domain;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Bean for provider
 */
public class Provider implements Serializable {

    private int providerId;
    private LocalDateTime contractStart;
    private LocalDateTime contractExpiry;

    public Provider() {
    }

    public Provider(int providerId, LocalDateTime contractStart, LocalDateTime contractExpiry) {
        this.providerId = providerId;
        this.contractStart = contractStart;
        this.contractExpiry = contractExpiry;
    }

    public int getProviderId() {
        return providerId;
    }

    public void setProviderId(int providerId) {
        this.providerId = providerId;
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
        return getProviderId() == provider.getProviderId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getProviderId());
    }

    @Override
    public String toString() {
        return "Provider{" +
                "providerId=" + providerId +
                ", contractStart=" + contractStart +
                ", contractExpiry=" + contractExpiry +
                '}';
    }

}
