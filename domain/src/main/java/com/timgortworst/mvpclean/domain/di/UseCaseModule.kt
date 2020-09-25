package com.timgortworst.mvpclean.domain.di

import com.timgortworst.mvpclean.domain.model.state.ErrorHandler
import com.timgortworst.mvpclean.domain.repository.MovieRepository
import com.timgortworst.mvpclean.domain.usecase.moviedetail.GetMovieDetailsUseCase
import com.timgortworst.mvpclean.domain.usecase.moviedetail.GetMovieDetailsUseCaseImpl
import com.timgortworst.mvpclean.domain.usecase.movielist.GetMoviesUseCase
import com.timgortworst.mvpclean.domain.usecase.movielist.GetMoviesUseCaseImpl
import dagger.Module
import dagger.Provides

@Module
class UseCaseModule {

    @Provides
    fun provideGetMoviesUseCase(
        movieRepository: MovieRepository,
        errorHandler: ErrorHandler
    ): GetMoviesUseCase {
        return GetMoviesUseCaseImpl(movieRepository, errorHandler)
    }

    @Provides
    fun provideGetMovieDetailsUseCase(
        movieRepository: MovieRepository
    ): GetMovieDetailsUseCase {
        return GetMovieDetailsUseCaseImpl(movieRepository)
    }
}



