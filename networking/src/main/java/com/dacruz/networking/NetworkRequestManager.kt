package com.dacruz.networking

class NetworkRequestManager(private val transformer: ExceptionTransformer) {

    suspend fun <T> executeRequest(request: suspend () -> T): T {
        return try {
            request()
        } catch (incoming: Throwable) {
            throw transformer.transform(incoming)
        }
    }
}