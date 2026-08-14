package cl.felipe.agrofrost.domain.entity;

import cl.felipe.agrofrost.domain.model.FrostRiskLevel;
import cl.felipe.agrofrost.domain.valueobject.CriticalTemperature;
import cl.felipe.agrofrost.domain.valueobject.Crop;
import cl.felipe.agrofrost.domain.valueobject.FieldId;
import cl.felipe.agrofrost.domain.valueobject.FieldName;
import cl.felipe.agrofrost.domain.valueobject.FrostAssessment;
import cl.felipe.agrofrost.domain.valueobject.MeasuredTemperature;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class FieldTest {

    private Field field() {
        return new Field(
                new FieldId("f-1"),
                new FieldName("North"),
                new Crop("Grapes"),
                new CriticalTemperature(0));
    }

    @Test
    void hasValueObjectIdentityAndAttributes() {
        Field field = field();

        assertEquals(new FieldId("f-1"), field.id());
        assertEquals(new FieldName("North"), field.name());
        assertEquals(new Crop("Grapes"), field.crop());
        assertEquals(new CriticalTemperature(0), field.criticalTemperature());
    }

    @ParameterizedTest
    @CsvSource({"-1.0, CRITICAL", "0.0, CRITICAL", "1.5, WARNING", "2.0, WARNING", "2.1, SAFE"})
    void assessesFrostAtExactBoundaries(double measured, FrostRiskLevel expected) {
        FrostAssessment assessment = field().assessFrost(new MeasuredTemperature(measured));

        assertEquals(expected, assessment.riskLevel());
    }

    @Test
    void considersFieldsEqualWhenTheyHaveTheSameFieldIdDespiteDifferentAttributes() {
        Field first = new Field(new FieldId("FIELD-001"), new FieldName("Huerto Los Robles"),
                new Crop("Manzanos"), new CriticalTemperature(0));
        Field second = new Field(new FieldId("FIELD-001"), new FieldName("Otro Campo"),
                new Crop("Cerezos"), new CriticalTemperature(2));

        assertEquals(first, second);
    }

    @Test
    void considersFieldsDifferentWhenTheyHaveDifferentFieldIdsDespiteEqualAttributes() {
        Field first = new Field(new FieldId("FIELD-001"), new FieldName("North"),
                new Crop("Grapes"), new CriticalTemperature(0));
        Field second = new Field(new FieldId("FIELD-002"), new FieldName("North"),
                new Crop("Grapes"), new CriticalTemperature(0));

        assertNotEquals(first, second);
    }

    @Test
    void equalFieldsHaveTheSameHashCode() {
        Field first = new Field(new FieldId("FIELD-001"), new FieldName("North"),
                new Crop("Grapes"), new CriticalTemperature(0));
        Field second = new Field(new FieldId("FIELD-001"), new FieldName("South"),
                new Crop("Cherries"), new CriticalTemperature(1));

        assertEquals(first.hashCode(), second.hashCode());
    }

    @Test
    void isEqualToItself() {
        Field field = field();

        assertEquals(field, field);
    }

    @Test
    void isNotEqualToNullOrAnotherType() {
        Field field = field();

        assertAll(
                () -> assertFalse(field.equals(null)),
                () -> assertFalse(field.equals("f-1")));
    }

    @Test
    void rejectsNullConstructionPartsAndMeasurement() {
        assertAll(
                () -> assertThrows(NullPointerException.class,
                        () -> new Field(null, new FieldName("n"), new Crop("c"), new CriticalTemperature(0))),
                () -> assertThrows(NullPointerException.class,
                        () -> new Field(new FieldId("i"), null, new Crop("c"), new CriticalTemperature(0))),
                () -> assertThrows(NullPointerException.class,
                        () -> new Field(new FieldId("i"), new FieldName("n"), null, new CriticalTemperature(0))),
                () -> assertThrows(NullPointerException.class,
                        () -> new Field(new FieldId("i"), new FieldName("n"), new Crop("c"), null)),
                () -> assertThrows(NullPointerException.class, () -> field().assessFrost(null)));
    }
}
