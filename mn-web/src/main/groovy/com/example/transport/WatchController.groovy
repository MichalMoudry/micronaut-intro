package com.example.transport

import com.example.database.IStore
import com.example.transport.errors.NotImplementedException
import com.example.transport.model.ListResponse
import groovy.transform.CompileStatic
import io.micronaut.http.MediaType
import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Delete
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.Put

@Controller('/account/watcher')
@CompileStatic
class WatchController {
    private final IStore store

    WatchController(IStore store) {
        this.store = store
    }

    @Get(produces = MediaType.APPLICATION_JSON)
    ListResponse get() {
        throw new NotImplementedException(
                "get() is not implemented"
        )
    }

    @Put(
            consumes = MediaType.APPLICATION_JSON,
            produces = MediaType.APPLICATION_JSON
    )
    ListResponse update(@Body ListResponse list) {
        // inMemoryStore.updateList()
        list
    }

    @Delete('/{accountId}')
    void removeAccountFromList(long accountId) {
        throw new NotImplementedException(
                "removeAccountFromList() is not implemented"
        )
        // store.deleteFromList()
    }
}
