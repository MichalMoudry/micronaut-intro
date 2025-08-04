package com.example.service

import groovy.transform.CompileStatic

@CompileStatic
class HelloMicronautService implements ITestService {
    @Override
    String helloFromService() {
        'Hello'
    }
}
