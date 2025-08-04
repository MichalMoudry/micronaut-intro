package com.example.service

import io.micronaut.context.annotation.Primary
import jakarta.inject.Singleton

@Primary
@Singleton
final class HelloWorldService implements ITestService {
    @Override
    String helloFromService() {
        'Hello world!'
    }
}
