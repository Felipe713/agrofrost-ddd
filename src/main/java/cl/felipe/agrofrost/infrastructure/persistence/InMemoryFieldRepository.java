package cl.felipe.agrofrost.infrastructure.persistence;
import cl.felipe.agrofrost.domain.entity.Field; import cl.felipe.agrofrost.domain.repository.FieldRepository; import cl.felipe.agrofrost.domain.valueobject.FieldId;
import java.util.*;
public final class InMemoryFieldRepository implements FieldRepository { private final Map<FieldId, Field> fields = new HashMap<>(); public void save(Field field) { Field nonNullField=Objects.requireNonNull(field, "field must not be null"); fields.put(nonNullField.id(), nonNullField); } public Optional<Field> findById(FieldId fieldId) { return Optional.ofNullable(fields.get(Objects.requireNonNull(fieldId, "fieldId must not be null"))); } public List<Field> findAll() { return List.copyOf(fields.values()); } }
