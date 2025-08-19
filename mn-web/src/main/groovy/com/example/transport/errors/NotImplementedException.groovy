package com.example.transport.errors

import groovy.transform.CompileStatic

@CompileStatic
final class NotImplementedException extends RuntimeException {
    NotImplementedException(String message) {
        super(message)
    }
}
