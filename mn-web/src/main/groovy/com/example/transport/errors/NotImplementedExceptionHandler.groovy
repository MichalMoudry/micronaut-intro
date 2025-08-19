package com.example.transport.errors

import groovy.transform.CompileStatic
import io.micronaut.context.annotation.Requires
import io.micronaut.http.HttpRequest
import io.micronaut.http.HttpResponse
import io.micronaut.http.HttpStatus
import io.micronaut.http.server.exceptions.ExceptionHandler
import jakarta.inject.Singleton

@Singleton
@Requires(classes = [
        NotImplementedException.class,
        ExceptionHandler.class
])
@CompileStatic
class NotImplementedExceptionHandler
        implements ExceptionHandler<NotImplementedException, HttpResponse<CustomError>> {
    @Override
    HttpResponse<CustomError> handle(
            HttpRequest request,
            NotImplementedException exception) {
        HttpResponse.badRequest(
                new CustomError(
                        HttpStatus.BAD_REQUEST.getCode(),
                        exception.getMessage()
                )
        )
    }
}
