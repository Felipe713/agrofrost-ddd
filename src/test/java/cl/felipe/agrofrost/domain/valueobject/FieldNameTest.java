package cl.felipe.agrofrost.domain.valueobject;
import cl.felipe.agrofrost.domain.exception.InvalidFieldNameException; import org.junit.jupiter.api.Test; import static org.junit.jupiter.api.Assertions.*;
class FieldNameTest { @Test void normalizesValidValue() { assertEquals("North Field", new FieldName(" North Field ").value()); } @Test void rejectsNull() { assertThrows(InvalidFieldNameException.class, () -> new FieldName(null)); } @Test void rejectsBlank() { assertThrows(InvalidFieldNameException.class, () -> new FieldName("  ")); } }
