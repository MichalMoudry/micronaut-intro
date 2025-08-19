package com.example.transport.errors

import com.fasterxml.jackson.annotation.JsonProperty
import groovy.transform.CompileStatic
import io.micronaut.serde.annotation.Serdeable

@Serdeable
@CompileStatic
final class CustomError {
    @JsonProperty('status_code')
    private int statusCode
    @JsonProperty('error_msg')
    private String errMessage

    CustomError(int statusCode, String errMessage) {
        this.statusCode = statusCode
        this.errMessage = errMessage
    }

    int getStatusCode() { statusCode }

    String getErrMessage() { errMessage }
}
