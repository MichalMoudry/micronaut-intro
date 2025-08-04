package com.example

import com.example.service.HelloWorldService
import io.micronaut.context.ApplicationContext
import io.micronaut.runtime.Micronaut
import groovy.transform.CompileStatic
import org.slf4j.Logger
import org.slf4j.LoggerFactory

@CompileStatic
class Application {
    private static final Logger LOG = LoggerFactory.getLogger(Application)

    static void main(String[] args) {
        ApplicationContext appCtx = Micronaut.run(Application, args)
        String response = appCtx.getBean(HelloWorldService).helloFromService()
        LOG.info("Service returned: {}", response)
    }

}
