package com.timgortworst.mvpclean.presentation.features.movie.list

import com.timgortworst.mvpclean.domain.usecase.movielist.GetMoviesUseCase
import com.timgortworst.mvpclean.presentation.features.movie.list.MovieListPresenter
import com.timgortworst.mvpclean.presentation.features.movie.list.MovieListFragment
import com.timgortworst.mvpclean.presentation.features.movie.list.MovieListView
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
