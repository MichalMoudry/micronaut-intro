package com.example.database

import com.example.database.model.Symbol
import groovy.transform.CompileStatic

@CompileStatic
interface IStore {
    Map<String, Symbol> getSymbols()
    Symbol getSymbol(String id)
    void addNewSymbol(String value)
}