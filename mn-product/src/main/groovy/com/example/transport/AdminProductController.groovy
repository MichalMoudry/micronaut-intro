package com.example.transport

import com.example.database.InMemoryStore
import com.example.transport.model.Product
import groovy.transform.CompileStatic
import io.micronaut.http.HttpStatus
import io.micronaut.http.MediaType
import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Post
import io.micronaut.http.annotation.Status

@CompileStatic
@Controller('admin/products')
class AdminProductController {

    private final InMemoryStore store

    AdminProductController(InMemoryStore store) {
        this.store = store
    }

    @Status(HttpStatus.CREATED)
    @Post(
            consumes = MediaType.APPLICATION_JSON,
            produces = MediaType.APPLICATION_JSON)
    Product addNewProduct(@Body Product product) {
        store.addProduct(product)
    }

}
