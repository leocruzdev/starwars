package com.dacruz.networking.errors

import com.dacruz.networking.ExceptionTransformer
import java.net.SocketTimeoutException
import java.net.http.HttpTimeoutException

class ConnectionTimeoutTransformer(override val next: ExceptionTransformer? = null) : ExceptionTransformer {
    override fun transform(incoming: Throwable): Throwable {
        return if (incoming is SocketTimeoutException) {
            HttpTimeoutException("A conexão expirou.")
        } else {
            next?.transform(incoming) ?: incoming
        }
    }
}