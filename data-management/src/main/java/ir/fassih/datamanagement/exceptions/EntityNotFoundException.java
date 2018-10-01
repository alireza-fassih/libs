package ir.fassih.datamanagement.exceptions;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class EntityNotFoundException extends RuntimeException {

    private final Class<?> entityClass;


    @Override
    public String getMessage() {
        return "entity not found " + entityClass.getName();
    }

}
