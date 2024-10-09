package ai.shreds.domain.exceptions;

public class DomainValidationException extends Exception {

    private static final long serialVersionUID = 1L;

    public DomainValidationException() {
        super();
    }

    public DomainValidationException(String message) {
        super(message);
    }
}