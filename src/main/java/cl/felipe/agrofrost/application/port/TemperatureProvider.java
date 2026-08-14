package cl.felipe.agrofrost.application.port;

import cl.felipe.agrofrost.domain.valueobject.FieldId;
import cl.felipe.agrofrost.domain.valueobject.MeasuredTemperature;

public interface TemperatureProvider {

    MeasuredTemperature getCurrentTemperature(FieldId fieldId);
}
