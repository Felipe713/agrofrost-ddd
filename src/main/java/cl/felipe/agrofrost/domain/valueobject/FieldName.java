package cl.felipe.agrofrost.domain.valueobject;

import cl.felipe.agrofrost.domain.exception.InvalidFieldNameException;

public record FieldName(String value) {

    public FieldName {
        if (value == null || value.trim().isEmpty()) {
            throw new InvalidFieldNameException("Field name must not be blank.");
        }
        value = value.trim();
    }
}
