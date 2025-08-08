package com.example

import com.example.transport.model.Product
import com.example.transport.model.ProductType
import groovy.transform.CompileStatic
import io.micronaut.http.client.HttpClient
import io.micronaut.http.client.annotation.Client
import io.micronaut.test.extensions.junit5.annotation.MicronautTest
import jakarta.inject.Inject
import org.junit.jupiter.api.Test
import org.slf4j.Logger
import org.slf4j.LoggerFactory

@MicronautTest
@CompileStatic
final class ProductControllerTest {

    private static final Logger LOG =
            LoggerFactory.getLogger(ProductControllerTest)

    @Inject
    @Client('/products')
    private HttpClient httpClient

    @Test
    void testProductsEndpoint() {
        Product[] response = httpClient.toBlocking().retrieve('/', Product[])
        LOG.debug('Retrieved products: {}', logProducts(response))

        assert response.size() == 10
    }

    @Test
    void testProductsMaxFilter() {
        int expectedSize = 5
        Product[] response = httpClient
                .toBlocking()
                .retrieve("/filter?max=$expectedSize", Product[])

        assert response.size() == expectedSize
    }

    @Test
    void testProductsOffsetAndMaxFilter() {
        int expectedSize = 2
        int targetOffset = 6
        Product[] response = httpClient
                .toBlocking()
                .retrieve(
                        "/filter?max=$expectedSize&offset=$targetOffset",
                        Product[]
                )

        assert response.size() == expectedSize
        assert response[0].id == targetOffset
    }

    @Test
    void testProductEndpoint() {
        Product response = httpClient.toBlocking().retrieve('/0', Product)

        assert response != null
        assert response.id == 0
        assert response.type == ProductType.COFFEE
    }

    private static String logProducts(Product[] products) throws IOException {
        StringBuilder sb = new StringBuilder()
        sb.append((char)'[')
        for (Product product in products) {
            sb.append("$product\n")
        }
        sb.append((char)']')
        sb
    }

}
