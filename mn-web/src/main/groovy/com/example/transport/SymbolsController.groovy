package com.example.transport

import com.example.database.InMemoryStore
import com.example.database.model.Symbol
import com.example.transport.model.SymbolResponse
import groovy.transform.CompileStatic
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.PathVariable
import io.micronaut.http.annotation.QueryValue

@Controller('/symbols')
@CompileStatic
class SymbolsController {
    private final InMemoryStore inMemoryStore

    SymbolsController(InMemoryStore inMemoryStore) {
        this.inMemoryStore = inMemoryStore
    }

    @Get
    List<SymbolResponse> getAll() {
        inMemoryStore
                .getSymbols()
                .values()
                .stream()
                .map { Symbol i -> new SymbolResponse(i.value) }
                .toList()
    }

    @Get('/{value}')
    Optional<SymbolResponse> getSingle(@PathVariable String value) {
        Symbol resp = inMemoryStore.getSymbol(value)
        if (resp == null) {
            return Optional.empty()
        }
        return Optional.of(new SymbolResponse(resp.value))
    }

    @Get('/filter{?max,offset}')
    List<SymbolResponse> getSymbolsWithFilter(
            @QueryValue Optional<Integer> max,
            @QueryValue Optional<Integer> offset) {
        Collection<Symbol> values = inMemoryStore.getSymbols().values()
        values.stream()
                .skip(offset.orElse(0))
                .limit(max.orElse(values.size()))
                .map { Symbol i -> new SymbolResponse(i.value) }
                .toList()
    }
}
