package com.hexad.handler;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author R.Fattahi
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(Include.NON_NULL)
public class ErrorMessage {

    public ErrorMessage(int status, String error, String message) {
        this.timestamp = new Date();
        this.status = status;
        this.error = error;
        this.message = message;
    }

    private Date timestamp;

    private int status;

    private String error;

    private String message;
}