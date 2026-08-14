package cl.felipe.agrofrost.domain.valueobject;
import cl.felipe.agrofrost.domain.exception.InvalidCropException; import org.junit.jupiter.api.Test; import static org.junit.jupiter.api.Assertions.*;
class CropTest { @Test void normalizesValidValue() { assertEquals("Grapes", new Crop(" Grapes ").value()); } @Test void rejectsNull() { assertThrows(InvalidCropException.class, () -> new Crop(null)); } @Test void rejectsBlank() { assertThrows(InvalidCropException.class, () -> new Crop("  ")); } }
