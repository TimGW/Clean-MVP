package com.timgortworst.mvpclean.presentation.features.movie.di

import com.timgortworst.mvpclean.domain.usecase.movielist.GetMoviesUseCase
import com.timgortworst.mvpclean.presentation.features.movie.presenter.MovieListPresenter
import com.timgortworst.mvpclean.presentation.features.movie.view.MovieListFragment
import com.timgortworst.mvpclean.presentation.features.movie.view.MovieListView
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
abstract class MovieListModule {

    @Binds
    abstract fun provideView(fragment: MovieListFragment): MovieListView

    @Module
    companion object {

        @Provides
        @JvmStatic
        fun providePresenter(
            view: MovieListView,
            getMoviesUseCase: GetMoviesUseCase
        ): MovieListPresenter {
            return MovieListPresenter(view, getMoviesUseCase)
        }
    }
}
