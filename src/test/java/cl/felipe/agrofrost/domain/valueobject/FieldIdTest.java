package cl.felipe.agrofrost.domain.valueobject;
import cl.felipe.agrofrost.domain.exception.InvalidFieldIdException; import org.junit.jupiter.api.Test; import static org.junit.jupiter.api.Assertions.*;
class FieldIdTest { @Test void normalizesValidValue() { assertEquals("field-1", new FieldId(" field-1 ").value()); } @Test void rejectsNull() { assertThrows(InvalidFieldIdException.class, () -> new FieldId(null)); } @Test void rejectsBlank() { assertThrows(InvalidFieldIdException.class, () -> new FieldId("  ")); } }
