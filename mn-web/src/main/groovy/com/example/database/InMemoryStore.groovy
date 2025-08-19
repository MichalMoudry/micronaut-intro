package com.example.database

import com.example.database.model.Symbol
import com.github.javafaker.Faker
import groovy.transform.CompileStatic
import jakarta.annotation.PostConstruct
import jakarta.inject.Singleton
import org.slf4j.Logger
import org.slf4j.LoggerFactory

@Singleton
@CompileStatic
class InMemoryStore implements IStore {
    private static final Logger LOG = LoggerFactory.getLogger(InMemoryStore)
    private final Map<String, Symbol> symbols = [:]

    @PostConstruct
    void initialize() {
        def faker = new Faker()
        for (int i in 0..9) {
            addNewSymbol(faker.stock().nsdqSymbol())
        }
    }

    @Override
    Map<String, Symbol> getSymbols() {
        symbols
    }

    @Override
    Symbol getSymbol(String id) {
        symbols.get(id)
    }

    @Override
    void addNewSymbol(String value) {
        Symbol symbol = new Symbol(value)
        symbols.put(symbol.value, symbol)
        LOG.debug("Added Symbol {}", symbol)
    }
}
