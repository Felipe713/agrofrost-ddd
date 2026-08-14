package cl.felipe.agrofrost.infrastructure.persistence;

import cl.felipe.agrofrost.domain.entity.Field;
import cl.felipe.agrofrost.domain.repository.FieldRepository;
import cl.felipe.agrofrost.domain.valueobject.FieldId;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

public final class InMemoryFieldRepository implements FieldRepository {

    private final Map<FieldId, Field> fields = new HashMap<>();

    @Override
    public void save(Field field) {
        Field nonNullField = Objects.requireNonNull(field, "field must not be null");
        fields.put(nonNullField.id(), nonNullField);
    }

    @Override
    public Optional<Field> findById(FieldId fieldId) {
        FieldId nonNullFieldId = Objects.requireNonNull(fieldId, "fieldId must not be null");
        return Optional.ofNullable(fields.get(nonNullFieldId));
    }

    @Override
    public List<Field> findAll() {
        return List.copyOf(fields.values());
    }
}
