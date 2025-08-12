package com.example.transport.model

import groovy.transform.CompileStatic
import io.micronaut.serde.annotation.Serdeable

@Serdeable
@CompileStatic
record UpdateProductRequest(String name, ProductType type) { }