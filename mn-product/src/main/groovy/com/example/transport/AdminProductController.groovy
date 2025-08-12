package com.example.transport

import com.example.database.InMemoryStore
import com.example.transport.model.Product
import com.example.transport.model.UpdateProductRequest
import groovy.transform.CompileStatic
import io.micronaut.http.HttpResponse
import io.micronaut.http.HttpStatus
import io.micronaut.http.MediaType
import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Delete
import io.micronaut.http.annotation.PathVariable
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

    @Post('{id}')
    Product updateProduct(
            @PathVariable int id,
            @Body UpdateProductRequest request) {
        Product product = new Product(id, request.name(), request.type())
        store.addProduct(product)
    }

    @Delete('{id}')
    HttpResponse<HttpStatus> deleteProduct(@PathVariable int id) {
        boolean wasDeleted = store.deleteProduct(id)
        if (!wasDeleted) {
            return HttpResponse.status(HttpStatus.BAD_REQUEST)
        }

        return HttpResponse.status(HttpStatus.OK)
    }

}
