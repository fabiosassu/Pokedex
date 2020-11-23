package it.fabiosassu.pokedex.util

import it.fabiosassu.pokedex.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.util.concurrent.TimeUnit
import javax.net.ssl.HostnameVerifier


object OkHttpClientUtils {

    fun getOkHttpClient(): OkHttpClient {
        return try {
            OkHttpClient.Builder()
                .addNetworkInterceptor(HttpLoggingInterceptor().apply {
                    level =
                        if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
                }).addInterceptor {
                    val request = it.request()
                    val builderInterceptor = request.newBuilder()
                    it.proceed(builderInterceptor.build())
                }
                .connectTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .hostnameVerifier(HostnameVerifier { _, _ -> true })
                .build()
        } catch (e: Exception) {
            throw RuntimeException(e)
        }
    }

}