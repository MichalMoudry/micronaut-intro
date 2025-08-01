package com.example

import groovy.transform.CompileStatic
import io.micronaut.http.HttpResponse
import io.micronaut.http.HttpStatus
import io.micronaut.http.client.HttpClient
import io.micronaut.http.client.annotation.Client
import io.micronaut.test.extensions.junit5.annotation.MicronautTest
import org.junit.jupiter.api.Test
import jakarta.inject.Inject

@MicronautTest
@CompileStatic
final class TestControllerTest {

    @Inject
    @Client('/')
    HttpClient httpClient

    @Test
    void helloWorldRespondsCorrectly() {
        String response = httpClient.toBlocking().retrieve('/hello')
        assert response == 'Hello world!'
    }

    @Test
    void helloWorldRespondsWithCorrectStatus() {
        HttpResponse<String> response = httpClient.toBlocking().exchange(
                '/hello',
                String
        )
        assert response.status == HttpStatus.OK
        assert response.getBody().get() == 'Hello world!'
    }

}
