package cl.felipe.agrofrost.domain.valueobject;

import cl.felipe.agrofrost.domain.exception.InvalidFieldIdException;

public record FieldId(String value) {

    public FieldId {
        if (value == null || value.trim().isEmpty()) {
            throw new InvalidFieldIdException("Field id must not be blank.");
        }
        value = value.trim();
    }
}
