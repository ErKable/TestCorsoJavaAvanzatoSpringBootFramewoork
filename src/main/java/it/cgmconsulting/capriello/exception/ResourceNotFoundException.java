package it.cgmconsulting.capriello.exception;

import lombok.Getter;

@Getter
//classe generica per la cattura di eccezioni ResoultNotFound
//qualunque cosa non venga trovata verra parametrizzata nel costruttore

public class ResourceNotFoundException extends RuntimeException{
    private final String resourceName;
    private final String fieldName;
    private final Object fieldValue;

    public ResourceNotFoundException(String resourceName, String fieldName, Object fieldValue) {
        super(String.format("%s not found with %s: %s", resourceName, fieldName, fieldValue));
        this.resourceName = resourceName;
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
    }
}

