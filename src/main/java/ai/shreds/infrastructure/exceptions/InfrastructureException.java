package ai.shreds.infrastructure.exceptions;

public class InfrastructureException extends Exception {

    private static final long serialVersionUID = 1L;

    public InfrastructureException(String message) {
        super(message);
    }

    public InfrastructureException(String message, Throwable cause) {
        super(message, cause);
    }
}