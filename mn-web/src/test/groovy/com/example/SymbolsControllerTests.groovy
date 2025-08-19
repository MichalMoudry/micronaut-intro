package com.example

import com.example.database.InMemoryStore
import com.example.transport.model.SymbolResponse
import io.micronaut.http.HttpStatus
import io.micronaut.http.client.HttpClient
import io.micronaut.http.client.annotation.Client
import io.micronaut.json.tree.JsonNode
import io.micronaut.test.extensions.spock.annotation.MicronautTest
import jakarta.inject.Inject
import spock.lang.Specification

@MicronautTest
class SymbolsControllerTests extends Specification {

    @Inject
    @Client('/symbols')
    private HttpClient httpClient

    @Inject
    private InMemoryStore inMemoryStore

    void 'get all symbols returns a list'() {
        def response = httpClient
                .toBlocking()
                .exchange('/', JsonNode)
        JsonNode body = response.body()

        expect:
        response.getStatus() == HttpStatus.OK
        body.size() == 10
    }

    void 'get single returns a correct response'() {
        def expectedSymbol = new SymbolResponse('TEST')
        inMemoryStore.addNewSymbol(expectedSymbol.value)

        def response = httpClient
                .toBlocking()
                .exchange("/${expectedSymbol.value}", SymbolResponse)
        SymbolResponse body = response.body()

        expect:
        response.getStatus() == HttpStatus.OK
        body.getValue() == expectedSymbol.value
    }

    void 'test getSymbolsWithFilter'() {
        def blockingClient = httpClient.toBlocking()
        int expectedNumber = 10

        def response = blockingClient.exchange(
                "/filter?max=$expectedNumber",
                JsonNode
        )
        def body = response.body()

        int offset = 7
        def offsetResponse = blockingClient.exchange(
                "/filter?offset=$offset",
                JsonNode
        )
        def offsetBody = offsetResponse.body()

        expect:
        response.getStatus() == HttpStatus.OK
        body.size() == expectedNumber

        offsetResponse.getStatus() == HttpStatus.OK
        offsetBody.size() == expectedNumber - offset
    }

}
