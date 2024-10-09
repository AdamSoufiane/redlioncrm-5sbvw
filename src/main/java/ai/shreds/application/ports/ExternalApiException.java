package ai.shreds.application.ports;

/**
 * Custom exception for handling errors related to external API interactions.
 */
public class ExternalApiException extends Exception {

    /**
     * Constructs a new ExternalApiException with the specified detail message.
     *
     * @param message the detail message
     */
    public ExternalApiException(String message) {
        super(message);
    }

    /**
     * Constructs a new ExternalApiException with the specified detail message and cause.
     *
     * @param message the detail message
     * @param cause the cause of the exception
     */
    public ExternalApiException(String message, Throwable cause) {
        super(message, cause);
    }
}