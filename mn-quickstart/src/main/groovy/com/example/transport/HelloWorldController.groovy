package com.example.transport

import com.example.config.ITranslationConfig
import com.example.service.IPrintService
import groovy.transform.CompileStatic
import io.micronaut.context.annotation.Property
import io.micronaut.http.MediaType
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get

@Controller('/hello')
@CompileStatic
class HelloWorldController {

    private final IPrintService printService
    private final String configVal
    private final ITranslationConfig translationConfig

    HelloWorldController(
            IPrintService printService,
            @Property(name = 'hello.world.message') String configVal,
            ITranslationConfig translationConfig) {
        this.printService = printService
        this.configVal = configVal
        this.translationConfig = translationConfig
    }

    @Get(produces = MediaType.TEXT_PLAIN)
    String helloWorld() {
        printService.hello()
    }

    @Get(uri = 'config', produces = MediaType.TEXT_PLAIN)
    String helloConfig() {
        configVal
    }

    @Get(uri = 'translation', produces = MediaType.APPLICATION_JSON)
    ITranslationConfig helloTranslation() {
        translationConfig
    }

}
