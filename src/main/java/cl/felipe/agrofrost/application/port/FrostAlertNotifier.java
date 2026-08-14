package cl.felipe.agrofrost.application.port;

import cl.felipe.agrofrost.domain.valueobject.FrostAssessment;

public interface FrostAlertNotifier {

    void sendCriticalAlert(FrostAssessment assessment);
}
