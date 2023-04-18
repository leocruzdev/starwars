package com.dacruz.networking

import com.dacruz.networking.errors.ConnectionTimeoutTransformer
import com.dacruz.networking.errors.HttpNotFoundTransformer
import com.dacruz.networking.errors.MultiExceptionTransformer


val transformer = MultiExceptionTransformer(
    listOf(
        ConnectionTimeoutTransformer(),
        HttpNotFoundTransformer()
    )
)

suspend fun <T> executeRequest(
    request: suspend () -> T
): T {
    return try {
        request()
    } catch (incoming: Throwable) {
        throw transformer.transform(incoming)
    }
}