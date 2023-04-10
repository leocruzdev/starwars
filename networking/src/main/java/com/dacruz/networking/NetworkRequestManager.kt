package com.dacruz.networking

import com.dacruz.networking.errors.ConnectionTimeoutTransformer


val transformer: ExceptionTransformer = ConnectionTimeoutTransformer()

suspend fun <T> executeRequest(
    request: suspend () -> T
): T {
    return try {
        request()
    } catch (incoming: Throwable) {
        throw transformer.transform(incoming)
    }
}