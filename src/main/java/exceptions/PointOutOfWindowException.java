package exceptions;

/**
 * Exception that is thrown if either x- or y-coordinates or both are below zero.
 */
public class PointOutOfWindowException extends Exception {
    private static final long serialVersionUID = 42L;
    /**
     * Constructor.
     */
    public PointOutOfWindowException() {

    }

    /**
     * Constructor supplied with a message.
     */
    public PointOutOfWindowException(String message) {
        super(message);
    }
}
