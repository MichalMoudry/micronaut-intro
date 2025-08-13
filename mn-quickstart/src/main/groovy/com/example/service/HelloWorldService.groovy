package com.example.service

import groovy.transform.CompileStatic
import jakarta.inject.Singleton

@Singleton
@CompileStatic
class HelloWorldService implements IPrintService {

    @Override
    String hello() {
        'Hello from Hello service!'
    }

}
