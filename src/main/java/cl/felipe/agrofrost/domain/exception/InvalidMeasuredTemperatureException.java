package cl.felipe.agrofrost.domain.exception;

public final class InvalidMeasuredTemperatureException extends IllegalArgumentException {

    public InvalidMeasuredTemperatureException(String message) {
        super(message);
    }
}
