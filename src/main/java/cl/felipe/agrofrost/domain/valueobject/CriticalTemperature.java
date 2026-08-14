package cl.felipe.agrofrost.domain.valueobject;
import cl.felipe.agrofrost.domain.exception.InvalidCriticalTemperatureException;
public record CriticalTemperature(double value) { public CriticalTemperature { if (!Double.isFinite(value) || value < -10.0 || value > 10.0) throw new InvalidCriticalTemperatureException("Critical temperature must be finite and between -10.0 and 10.0 Celsius."); } }
