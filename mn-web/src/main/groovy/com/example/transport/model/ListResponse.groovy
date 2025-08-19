package com.example.transport.model

import groovy.transform.CompileStatic
import io.micronaut.serde.annotation.Serdeable

@Serdeable
@CompileStatic
final class ListResponse {
    private List<SymbolResponse> symbols

    ListResponse() {
        symbols = []
    }

    List<SymbolResponse> getSymbols() {
        symbols
    }
}
