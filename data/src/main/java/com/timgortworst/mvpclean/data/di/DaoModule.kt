package com.timgortworst.mvpclean.data.di

import com.timgortworst.mvpclean.data.database.AppDatabase
import com.timgortworst.mvpclean.data.database.LocalDataSourceMovie
import dagger.Module
import dagger.Provides

@Module
class DaoModule {

    @Provides
    fun provideMovieDao(database: AppDatabase): LocalDataSourceMovie {
        return database.movieDao()
    }
}