package ba.unsa.etf.rpr.domain;

/**
 * Interface used to force beans to have id fields
 *
 */
public interface Idable {

    void setId(int id);

    int getId();

}
