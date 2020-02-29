package com.hexad.exception;


/** for throwing a exception by a programmer if there is something wrong by end user
 * @author R.Fattahi
 */
public class BusinessException extends RuntimeException {

    public BusinessException() {

    }

    public BusinessException(String message) {
        super(message);
    }

}
