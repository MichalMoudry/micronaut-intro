package com.example.transport

import com.example.service.ITestService
import groovy.transform.CompileStatic
import io.micronaut.http.MediaType
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get

@Controller('/hello')
@CompileStatic
class TestController {
    private final ITestService service

    TestController(final ITestService service) {
        this.service = service
    }

    @Get(produces = MediaType.TEXT_PLAIN)
    String helloWorld() {
        // 'Hello world!'
        service.helloFromService()
    }
}
