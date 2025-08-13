package com.example

import groovy.transform.CompileStatic
import io.micronaut.http.HttpResponse
import io.micronaut.http.HttpStatus
import io.micronaut.http.client.HttpClient
import io.micronaut.http.client.annotation.Client
import io.micronaut.json.tree.JsonNode
import io.micronaut.test.extensions.junit5.annotation.MicronautTest
import jakarta.inject.Inject
import org.junit.jupiter.api.Test

@MicronautTest
@CompileStatic
final class HelloWorldControllerTests {

    @Inject
    @Client('/')
    private HttpClient client

    @Test
    void helloWorldEndpointRespondsWithCorrectContent() {
        HttpResponse<String> response = client.toBlocking().exchange(
                '/hello',
                String
        )
        assert response.status == HttpStatus.OK

        Optional<String> responseBody = response.body
        assert responseBody.present
        assert responseBody.get() == 'Hello from Hello service!'

    }

    @Test
    void helloWorldEndpointRespondsWithConfigValue() {
        HttpResponse<String> response = client.toBlocking().exchange(
                '/hello/config',
                String
        )
        assert response.status == HttpStatus.OK

        Optional<String> responseBody = response.body
        assert responseBody.present
        assert responseBody.get() == '"Hello from config"'
    }

    @Test
    void testHelloWorldTranslationEndpoint() {
        HttpResponse<JsonNode> response = client
                .toBlocking()
                .exchange('/hello/translation', JsonNode)
        assert response.status == HttpStatus.OK
    }

}
