package com.example.database

import com.example.transport.model.Product
import com.example.transport.model.ProductType
import groovy.transform.CompileStatic
import jakarta.annotation.PostConstruct
import jakarta.inject.Singleton
import net.datafaker.Faker
import org.slf4j.Logger
import org.slf4j.LoggerFactory

@Singleton
@CompileStatic
class InMemoryStore {

    private static final Logger LOG = LoggerFactory.getLogger(InMemoryStore)
    private final Faker faker = new Faker()
    private final Map<Integer, Product> productMap = [:]

    @PostConstruct
    void initialise() {
        (0..9).forEach { int i -> addProduct(i) }
    }

    Map<Integer, Product> getProducts() {
        productMap
    }

    Product addProduct(Product product) {
        products.put(product.id, product)
        products.get(product.id)
    }

    void addProduct(int id) {
        Product product = new Product(
                id,
                faker.coffee().blendName(),
                ProductType.COFFEE
        )
        products.put(id, product)
        LOG.debug('Added a product {}', product)
    }
}
