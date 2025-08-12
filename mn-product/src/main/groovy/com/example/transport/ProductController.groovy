package com.example.transport

import com.example.database.InMemoryStore
import com.example.transport.model.Product
import com.example.transport.model.ProductType
import groovy.transform.CompileStatic
import io.micronaut.http.HttpRequest
import io.micronaut.http.HttpResponse
import io.micronaut.http.HttpStatus
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Error
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.PathVariable
import io.micronaut.http.annotation.QueryValue
import io.micronaut.http.hateoas.JsonError
import org.slf4j.Logger
import org.slf4j.LoggerFactory

@CompileStatic
@Controller('/products')
class ProductController {

    private static final Logger LOG = LoggerFactory.getLogger(ProductController)
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

    @Get('/type/{type}')
    List<Product> getProductsByType(@PathVariable String type) {
        ProductType productType = ProductType.valueOf(type)
        store.getProducts()
                .values()
                .stream()
                .filter {Product i -> i.type == productType}
                .toList()
    }

    @Error
    HttpResponse<JsonError> onError(
            HttpRequest<?> request,
            IllegalArgumentException e) {
        LOG.debug("Local err handler...")
        String supportedProducts = Arrays.toString(ProductType.values())
        JsonError err = new JsonError(
                "Invalid product type: ${e.message}. Supported products are: $supportedProducts"
        )

        HttpResponse
                .status(HttpStatus.BAD_REQUEST)
                .body(err)
    }

}
