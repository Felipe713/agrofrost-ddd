package cl.felipe.agrofrost.application.usecase;

import cl.felipe.agrofrost.application.port.FrostAlertNotifier;
import cl.felipe.agrofrost.application.port.TemperatureProvider;
import cl.felipe.agrofrost.domain.entity.Field;
import cl.felipe.agrofrost.domain.exception.FieldNotFoundException;
import cl.felipe.agrofrost.domain.model.FrostRiskLevel;
import cl.felipe.agrofrost.domain.repository.FieldRepository;
import cl.felipe.agrofrost.domain.valueobject.FieldId;
import cl.felipe.agrofrost.domain.valueobject.FrostAssessment;
import cl.felipe.agrofrost.domain.valueobject.MeasuredTemperature;
import java.util.Objects;

public final class MonitorFieldUseCase {
    private final FieldRepository fieldRepository;
    private final TemperatureProvider temperatureProvider;
    private final FrostAlertNotifier frostAlertNotifier;

    public MonitorFieldUseCase(FieldRepository fieldRepository, TemperatureProvider temperatureProvider,
                              FrostAlertNotifier frostAlertNotifier) {
        this.fieldRepository = Objects.requireNonNull(fieldRepository);
        this.temperatureProvider = Objects.requireNonNull(temperatureProvider);
        this.frostAlertNotifier = Objects.requireNonNull(frostAlertNotifier);
    }

    public FrostAssessment execute(FieldId fieldId) {
        Objects.requireNonNull(fieldId, "fieldId must not be null");
        Field field = fieldRepository.findById(fieldId)
                .orElseThrow(() -> new FieldNotFoundException("Field with id '" + fieldId.value() + "' was not found."));
        MeasuredTemperature measuredTemperature = Objects.requireNonNull(
                temperatureProvider.getCurrentTemperature(fieldId), "temperatureProvider returned null");
        FrostAssessment assessment = field.assessFrost(measuredTemperature);
        if (assessment.riskLevel() == FrostRiskLevel.CRITICAL) {
            frostAlertNotifier.sendCriticalAlert(assessment);
        }
        return assessment;
    }
}
