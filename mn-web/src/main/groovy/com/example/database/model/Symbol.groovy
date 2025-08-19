package com.example.database.model

import groovy.transform.CompileStatic

@CompileStatic
class Symbol {
    private String value

    Symbol() { }

    Symbol(String value) {
        this.value = value
    }

    String getValue() {
        value
    }

    void setValue(String value) {
        this.value = value
    }

    @Override
    String toString() {
        "Symbol[value='$value']"
    }
}
