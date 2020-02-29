package com.hexad.handler;

import com.hexad.exception.BeanValidationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * During throwing a BeanValidationException this handler can be a specific message for seeing what fields are mandatory.
 *
 * @author R.Fattahi
 */
@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class BeanValidationExceptionHandler extends ResponseEntityExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(BeanValidationExceptionHandler.class);

    @ExceptionHandler({BeanValidationException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected ResponseEntity<Object> handleInvalidRequest(BeanValidationException exception, WebRequest request) {
        LOGGER.warn("BEAN VALIDATION FAILED! {}", exception.getMessage());
        List<ValidationMessage> errorResources = new ArrayList<>();
        BindingResult results = exception.getBindingResult();
        if (results != null) {
            for (FieldError error : results.getFieldErrors()) {
                ValidationMessage errorResource = ValidationMessage.builder().field(error.getField())
                        .message(error.getDefaultMessage()).build();
                errorResources.add(errorResource);
            }
        } else {
            ValidationMessage errorResource = new ValidationMessage();
            errorResource.setMessage(exception.getMessage());
            errorResources.add(errorResource);
        }
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return handleExceptionInternal(exception, errorResources, headers, HttpStatus.BAD_REQUEST, request);
    }
}
