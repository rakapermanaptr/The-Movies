package com.example.themovies.di.module

import com.example.themovies.data.TheMoviesRepository
import com.example.themovies.data.source.remote.RemoteDataSource
import com.example.themovies.data.source.remote.network.TheMoviesService
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class AppModule {

    companion object {
        const val API_KEY = "6d90617eed798d4678c51320e816bae1"
        const val BASE_URL = "https://api.themoviedb.org/3/"
        const val CONNECT_TIMEOUT: Long = 60
        const val READ_TIMEOUT: Long = 60
        const val WRITE_TIMEOUT: Long = 60
    }

    @Provides
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        return interceptor
    }

    @Provides
    fun provideInterceptor(): Interceptor {
        return Interceptor { chain ->
            val originalRequest = chain.request()
            val originalUrl = originalRequest.url()
            val url = originalUrl.newBuilder()
                .addQueryParameter("api_key", API_KEY)
                .build()

            val requestBuilder = originalRequest.newBuilder().url(url)
            val request = requestBuilder.build()
            chain.proceed(request)
        }
    }

    @Provides
    fun provideOkHttpClient(
        httpLoggingInterceptor: HttpLoggingInterceptor,
        interceptor: Interceptor
    ): OkHttpClient {
        val httpClient = OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .addInterceptor(interceptor)
            .connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
            .retryOnConnectionFailure(true)
        return httpClient.build()
    }

    @Provides
    fun provideMoshiConverter(): Moshi {
        return Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
    }

    @Singleton
    @Provides
    fun provideRetrofitInstance(httpClient: OkHttpClient, moshi: Moshi): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .client(httpClient)
            .build()
    }

    @Singleton
    @Provides
    fun provideTheMoviesRepository(remoteDataSource: RemoteDataSource): TheMoviesRepository {
        return TheMoviesRepository(remoteDataSource)
    }

    @Singleton
    @Provides
    fun provideRemoteDataSource(service: TheMoviesService): RemoteDataSource {
        return RemoteDataSource(service)
    }

    @Singleton
    @Provides
    fun provideTheMoviesService(retrofit: Retrofit): TheMoviesService {
        return retrofit.create(TheMoviesService::class.java)
    }
}