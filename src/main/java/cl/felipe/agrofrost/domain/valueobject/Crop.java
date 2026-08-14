package cl.felipe.agrofrost.domain.valueobject;

import cl.felipe.agrofrost.domain.exception.InvalidCropException;

public record Crop(String value) {

    public Crop {
        if (value == null || value.trim().isEmpty()) {
            throw new InvalidCropException("Crop must not be blank.");
        }
        value = value.trim();
    }
}
