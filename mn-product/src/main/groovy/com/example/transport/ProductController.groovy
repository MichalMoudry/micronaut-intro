package com.example.transport

import com.example.database.InMemoryStore
import com.example.transport.model.Product
import groovy.transform.CompileStatic
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.PathVariable
import io.micronaut.http.annotation.QueryValue

@CompileStatic
@Controller('/products')
class ProductController {

    private final InMemoryStore store

    ProductController(InMemoryStore store) {
        this.store = store
    }

    @Get
    List<Product> getProducts() {
        new ArrayList<>(store.getProducts().values())
    }

    @Get('{id}')
    Optional<Product> getProduct(@PathVariable int id) {
        store.getProducts().values()
                .stream()
                .filter { Product i -> i.id == id }
                .findFirst()
    }

    @Get('/filter{?max,offset}')
    List<Product> getFilteredProducts(
            @QueryValue Optional<Integer> max,
            @QueryValue Optional<Integer> offset) {
        List<Product> result = store
                .getProducts()
                .values()
                .stream()
                .skip(offset.orElse(0))
                .limit(max.orElse(0))
                .toList()
        result
    }

}
