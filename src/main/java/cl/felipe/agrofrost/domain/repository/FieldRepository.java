package cl.felipe.agrofrost.domain.repository;
import cl.felipe.agrofrost.domain.entity.Field;
import cl.felipe.agrofrost.domain.valueobject.FieldId;
import java.util.List; import java.util.Optional;
public interface FieldRepository { void save(Field field); Optional<Field> findById(FieldId fieldId); List<Field> findAll(); }
