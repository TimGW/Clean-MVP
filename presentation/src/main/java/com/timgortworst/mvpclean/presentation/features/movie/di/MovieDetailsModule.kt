package com.timgortworst.mvpclean.presentation.features.movie.di

import com.timgortworst.mvpclean.domain.usecase.moviedetail.GetMovieDetailsUseCase
import com.timgortworst.mvpclean.presentation.features.movie.presenter.MovieDetailsPresenter
import com.timgortworst.mvpclean.presentation.features.movie.view.MovieDetailsFragment
import com.timgortworst.mvpclean.presentation.features.movie.view.MovieDetailsView
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
