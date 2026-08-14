package cl.felipe.agrofrost.domain.entity;
import cl.felipe.agrofrost.domain.model.FrostRiskLevel;
import cl.felipe.agrofrost.domain.valueobject.*;
import java.util.Objects;
/** Aggregate Root of the Frost Monitoring Context. */
public final class Field {
    public static final double WARNING_MARGIN_CELSIUS = 2.0;
    private final FieldId id; private final FieldName name; private final Crop crop; private final CriticalTemperature criticalTemperature;
    public Field(FieldId id, FieldName name, Crop crop, CriticalTemperature criticalTemperature) { this.id=Objects.requireNonNull(id, "id must not be null"); this.name=Objects.requireNonNull(name, "name must not be null"); this.crop=Objects.requireNonNull(crop, "crop must not be null"); this.criticalTemperature=Objects.requireNonNull(criticalTemperature, "criticalTemperature must not be null"); }
    public FieldId id() { return id; } public FieldName name() { return name; } public Crop crop() { return crop; } public CriticalTemperature criticalTemperature() { return criticalTemperature; }
    public FrostAssessment assessFrost(MeasuredTemperature measuredTemperature) { Objects.requireNonNull(measuredTemperature, "measuredTemperature must not be null"); double measured=measuredTemperature.value(); double critical=criticalTemperature.value(); FrostRiskLevel riskLevel = measured <= critical ? FrostRiskLevel.CRITICAL : measured <= critical + WARNING_MARGIN_CELSIUS ? FrostRiskLevel.WARNING : FrostRiskLevel.SAFE; return new FrostAssessment(id, measuredTemperature, criticalTemperature, riskLevel); }
}
