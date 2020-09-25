package com.timgortworst.mvpclean.data.di

import com.timgortworst.mvpclean.data.network.RemoteDataSourceMovie
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class ApiModule {

    @Provides
    fun provideRemoteDataSourceMovie(retrofit: Retrofit): RemoteDataSourceMovie {
        return retrofit.create(RemoteDataSourceMovie::class.java)
    }
}