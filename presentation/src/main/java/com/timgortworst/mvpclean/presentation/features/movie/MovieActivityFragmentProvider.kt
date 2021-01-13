package com.timgortworst.mvpclean.presentation.features.movie

import com.timgortworst.mvpclean.presentation.features.movie.details.MovieDetailsModule
import com.timgortworst.mvpclean.presentation.features.movie.details.MovieDetailsFragment
import com.timgortworst.mvpclean.presentation.features.movie.list.MovieListFragment
import com.timgortworst.mvpclean.presentation.features.movie.list.MovieListModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MovieActivityFragmentProvider {

    @ContributesAndroidInjector(modules = [(MovieListModule::class)])
    abstract fun provideMovieListFragment(): MovieListFragment

    @ContributesAndroidInjector(modules = [(MovieDetailsModule::class)])
    abstract fun provideMovieDetailsFragment(): MovieDetailsFragment
}
