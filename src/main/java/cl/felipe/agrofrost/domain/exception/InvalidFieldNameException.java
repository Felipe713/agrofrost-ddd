package cl.felipe.agrofrost.domain.exception;

public final class InvalidFieldNameException extends IllegalArgumentException {

    public InvalidFieldNameException(String message) {
        super(message);
    }
}
