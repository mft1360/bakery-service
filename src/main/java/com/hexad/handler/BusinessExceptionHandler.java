package com.hexad.handler;

import com.hexad.exception.BusinessException;
import com.hexad.exception.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * for handing businessException that is threw by programmer.
 *
 * @author R.Fattahi
 */
@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class BusinessExceptionHandler extends ResponseEntityExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(BusinessExceptionHandler.class);

    @ExceptionHandler({BusinessException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected ResponseEntity<Object> handleBusinessException(BusinessException exception, WebRequest request) {
        LOGGER.warn("there is an exception {}", exception.getMessage());

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return handleExceptionInternal(exception, new ErrorMessage(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.name(), exception.getMessage()), headers,
                HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler({Exception.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected ResponseEntity<Object> handleGeneralException(Exception exception, WebRequest request) {
        LOGGER.warn("there is an exception {}", exception.getMessage());

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return handleExceptionInternal(exception, new ErrorMessage(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.name(), exception.getMessage()), headers,
                HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler({EntityNotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    protected ResponseEntity<Object> handleEntityNotFoundException(EntityNotFoundException exception, WebRequest request) {
        LOGGER.warn("data not found! {}", exception.getMessage());

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return handleExceptionInternal(exception, new ErrorMessage(HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND.name(), exception.getMessage()), headers, HttpStatus.NOT_FOUND,
                request);
    }

    @ExceptionHandler({DataIntegrityViolationException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected ResponseEntity<Object> handleDataIntegrityViolationException(DataIntegrityViolationException exception, WebRequest request) {
        LOGGER.warn("DataIntegrityViolationException while creating a entity: {}", exception.getMessage());

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return handleExceptionInternal(exception, new ErrorMessage(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.name(), exception.getMessage()), headers, HttpStatus.BAD_REQUEST,
                request);
    }

}
