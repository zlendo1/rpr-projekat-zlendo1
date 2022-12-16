package ba.unsa.etf.rpr.exception;

/**
 * My exception class for handling dao exceptions
 *
 */
public class DBHandleException extends Exception {

    /**
     * Constructs a new exception with the detail message
     * @param message - Detail message
     */
    public DBHandleException(String message) {
        super(message);
    }

    /**
     * Constructs a new exception with given cause
     * @param cause - Cause of exception
     */
    public DBHandleException(Throwable cause) {
        super(cause);
    }

}
