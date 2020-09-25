package com.timgortworst.mvpclean.data.di

import com.timgortworst.mvpclean.data.BuildConfig
import com.timgortworst.mvpclean.data.network.AuthHeaderInterceptor
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class NetworkModule {

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder().baseUrl(BuildConfig.BASE_URL).client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create()).build()
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(authInterceptor: AuthHeaderInterceptor): OkHttpClient {
        return OkHttpClient().newBuilder().addInterceptor(authInterceptor).build()
    }

    @Provides
    @Singleton
    fun provideAuthHeaderInterceptor(): AuthHeaderInterceptor {
        return AuthHeaderInterceptor(BuildConfig.API_KEY)
    }
}



