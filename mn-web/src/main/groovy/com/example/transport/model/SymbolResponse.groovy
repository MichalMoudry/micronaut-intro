package com.example.transport.model

import groovy.transform.CompileStatic
import io.micronaut.serde.annotation.Serdeable

@Serdeable
@CompileStatic
final class SymbolResponse {
    private String value

    SymbolResponse(String value) {
        this.value = value
    }

    String getValue() {
        value
    }
}
