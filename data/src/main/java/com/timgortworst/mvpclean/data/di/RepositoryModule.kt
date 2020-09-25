package com.timgortworst.mvpclean.data.di

import com.timgortworst.mvpclean.data.database.LocalDataSourceMovie
import com.timgortworst.mvpclean.data.error.ErrorHandlerImpl
import com.timgortworst.mvpclean.data.network.RemoteDataSourceMovie
import com.timgortworst.mvpclean.data.repository.MovieRepositoryImpl
import com.timgortworst.mvpclean.domain.model.state.ErrorHandler
import com.timgortworst.mvpclean.domain.repository.MovieRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Provides
    fun provideErrorHandler() : ErrorHandler = ErrorHandlerImpl()

    @Provides
    @Singleton
    fun provideMovieRepository(
        remoteDataSourceMovie: RemoteDataSourceMovie,
        localDataSourceMovie: LocalDataSourceMovie,
        errorHandler: ErrorHandler
    ): MovieRepository {
        return MovieRepositoryImpl(remoteDataSourceMovie, localDataSourceMovie, errorHandler)
    }
}
