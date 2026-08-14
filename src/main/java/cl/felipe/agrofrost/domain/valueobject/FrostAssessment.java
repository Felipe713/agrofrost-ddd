package cl.felipe.agrofrost.domain.valueobject;
import cl.felipe.agrofrost.domain.model.FrostRiskLevel;
import java.util.Objects;
public record FrostAssessment(FieldId fieldId, MeasuredTemperature measuredTemperature, CriticalTemperature criticalTemperature, FrostRiskLevel riskLevel) { public FrostAssessment { Objects.requireNonNull(fieldId, "fieldId must not be null"); Objects.requireNonNull(measuredTemperature, "measuredTemperature must not be null"); Objects.requireNonNull(criticalTemperature, "criticalTemperature must not be null"); Objects.requireNonNull(riskLevel, "riskLevel must not be null"); } }
