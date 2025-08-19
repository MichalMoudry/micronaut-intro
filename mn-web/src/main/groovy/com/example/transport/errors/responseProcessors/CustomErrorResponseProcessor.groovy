package com.example.transport.errors.responseProcessors

import com.example.transport.errors.CustomError
import groovy.transform.CompileStatic
import io.micronaut.core.annotation.NonNull
import io.micronaut.http.HttpStatus
import io.micronaut.http.MutableHttpResponse
import io.micronaut.http.server.exceptions.response.Error
import io.micronaut.http.server.exceptions.response.ErrorContext
import io.micronaut.http.server.exceptions.response.ErrorResponseProcessor

@CompileStatic
final class CustomErrorResponseProcessor
        implements ErrorResponseProcessor<CustomError> {
    @Override
    MutableHttpResponse<CustomError> processResponse(
            @NonNull ErrorContext errorContext,
            @NonNull MutableHttpResponse<?> baseResponse) {
        CustomError err
        HttpStatus status = baseResponse.getStatus()
        if (!errorContext.hasErrors()) {
            err = new CustomError(
                    status.getCode(),
                    status.name()
            )
        }
        else {
            Error firstErr = errorContext.getErrors().get(0)
            err = new CustomError(
                    status.getCode(),
                    firstErr.getMessage()
            )
        }

        MutableHttpResponse.badRequest(err)
    }
}
