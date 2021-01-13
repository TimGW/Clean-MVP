package com.timgortworst.mvpclean.presentation.features.movie.details

import com.timgortworst.mvpclean.domain.usecase.moviedetail.GetMovieDetailsUseCase
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
abstract class MovieDetailsModule {

    @Binds
    abstract fun provideView(fragment: MovieDetailsFragment): MovieDetailsView

    @Module
    companion object {

        @Provides
        @JvmStatic
        fun providePresenter(
            view: MovieDetailsView,
            getMovieDetailsUseCase: GetMovieDetailsUseCase
        ): MovieDetailsPresenter {
            return MovieDetailsPresenter(view, getMovieDetailsUseCase)
        }
    }
}
