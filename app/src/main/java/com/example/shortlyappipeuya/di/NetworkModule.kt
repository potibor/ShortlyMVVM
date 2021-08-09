package com.example.shortlyappipeuya.di

import android.app.Application
import android.content.Context
import com.example.shortlyappipeuya.data.remote.api.LinkAPI
import com.example.shortlyappipeuya.util.Constants
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    @Singleton
    fun provideRetrofit(client: OkHttpClient): Retrofit {
        return Retrofit.Builder().baseUrl(Constants.Network.BASE_URL).client(client)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build()
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(
        headerInterceptor: Interceptor,
        cache: Cache
    ): OkHttpClient {
        return OkHttpClient().newBuilder().apply {
            connectTimeout(Constants.Network.CONNECTION_TIMEOUT.toLong(), TimeUnit.SECONDS)
            readTimeout(Constants.Network.READ_TIMEOUT.toLong(), TimeUnit.SECONDS)
            writeTimeout(Constants.Network.WRITE_TIMEOUT.toLong(), TimeUnit.SECONDS)
            cache(cache)
            addInterceptor(headerInterceptor)
        }.build()
    }

    @Provides
    @Singleton
    fun provideHeaderInterceptor(): Interceptor {
        return Interceptor {
            val requestBuilder = it.request().newBuilder()
            it.proceed(requestBuilder.build())
        }
    }

    @Provides
    @Singleton
    internal fun provideCache(context: Context): Cache {
        val httpCacheDirectory = File(context.cacheDir.absolutePath, "HttpCache")
        return Cache(httpCacheDirectory, Constants.Network.CACHE_SIZE_BYTES)
    }

    @Provides
    @Singleton
    fun provideContext(application: Application): Context {
        return application.applicationContext
    }

    @Provides
    @Singleton
    fun provideLinkApi(retrofit: Retrofit): LinkAPI {
        return retrofit.create(LinkAPI::class.java)
    }
}