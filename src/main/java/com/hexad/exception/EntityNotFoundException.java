package com.hexad.exception;

/** for throwing a exception while an entity is null in data base.
 * @author R.Fattahi
 */
public class EntityNotFoundException extends RuntimeException {

    public EntityNotFoundException() {

    }

    public EntityNotFoundException(String message) {
        super(message);
    }

}
