package com.example.transport

import groovy.transform.CompileStatic
import io.micronaut.http.MediaType
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get

@Controller('/hello')
@CompileStatic
class TestController {
    @Get(produces = MediaType.TEXT_PLAIN)
    String helloWorld() {
        'Hello world!'
    }
}
