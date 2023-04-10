package com.dacruz.networking

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.Interceptor
import okhttp3.logging.HttpLoggingInterceptor
import java.util.concurrent.TimeUnit
import kotlin.reflect.KClass

object RetrofitBuilder {
    private const val apiURL = "https://swapi.dev/"

    private val moshi: Moshi by lazy {
        Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
    }

   private fun createInterceptor(): Interceptor {
       val loggingInterceptor = HttpLoggingInterceptor()
       loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
       return loggingInterceptor
   }
    private fun createOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(createInterceptor())
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .build()
    }

    fun <T : Any> createService(serviceClass: KClass<T>): T {
        val retrofit = Retrofit.Builder()
            .baseUrl(apiURL)
            .client(createOkHttpClient())
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()

        return retrofit.create(serviceClass.java)
    }
}