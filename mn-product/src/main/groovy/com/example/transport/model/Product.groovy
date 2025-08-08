package com.example.transport.model

import groovy.transform.CompileStatic
import io.micronaut.serde.annotation.Serdeable

@Serdeable
@CompileStatic
final class Product {
    int id
    String name
    ProductType type

    Product(int id, String name, ProductType type) {
        this.id = id
        this.name = name
        this.type = type
    }

    @Override
    String toString() {
        "Product[id=$id, name=$name, type=$type]"
    }
}