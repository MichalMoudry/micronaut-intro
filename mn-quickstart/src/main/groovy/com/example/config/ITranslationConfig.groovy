package com.example.config

import groovy.transform.CompileStatic
import io.micronaut.context.annotation.ConfigurationProperties
import io.micronaut.serde.annotation.Serdeable

@Serdeable
@ConfigurationProperties('hello.world.translation')
@CompileStatic
interface ITranslationConfig {

    String getDe()
    String getEn()

}