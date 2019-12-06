package exceptions;

/**
 * Exception that is used to indicate that an x- and y-coordinate lies out of game screen.
 */
public class PointOutOfWindowException extends Exception {
    private static final long serialVersionUID = 42L;

    /**
     * Constructor.
     */
    public PointOutOfWindowException() {
    }

    /**
     * Constructor supplied with a error message.
     */
    public PointOutOfWindowException(String message) {
        super(message);
    }
}
