package exceptions;

/**
 * Exception that is thrown if either x- or y-coordinates or both are below zero.
 */
public class PointOutOfWindowException extends Exception {
    /**
     * Constructor.
     */
    public PointOutOfWindowException() {}

    /**
     * Constructor supplied with a message.
     */
    public PointOutOfWindowException(String message) {
        super(message);
    }
}
