package cl.felipe.agrofrost.application.usecase;

import cl.felipe.agrofrost.domain.entity.Field;
import cl.felipe.agrofrost.domain.exception.DuplicateFieldException;
import cl.felipe.agrofrost.domain.repository.FieldRepository;
import java.util.Objects;

public final class RegisterFieldUseCase {
    private final FieldRepository fieldRepository;

    public RegisterFieldUseCase(FieldRepository fieldRepository) {
        this.fieldRepository = Objects.requireNonNull(fieldRepository);
    }

    public void execute(Field field) {
        Objects.requireNonNull(field, "field must not be null");
        if (fieldRepository.findById(field.id()).isPresent()) {
            throw new DuplicateFieldException("A field with id '" + field.id().value() + "' already exists.");
        }
        fieldRepository.save(field);
    }
}
