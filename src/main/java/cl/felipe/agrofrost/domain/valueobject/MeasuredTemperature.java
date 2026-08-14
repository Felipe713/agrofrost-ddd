package cl.felipe.agrofrost.domain.valueobject;

import cl.felipe.agrofrost.domain.exception.InvalidMeasuredTemperatureException;

public record MeasuredTemperature(double value) {

    public MeasuredTemperature {
        if (!Double.isFinite(value) || value < -50.0 || value > 60.0) {
            throw new InvalidMeasuredTemperatureException(
                    "Measured temperature must be finite and between -50.0 and 60.0 Celsius.");
        }
    }
}
