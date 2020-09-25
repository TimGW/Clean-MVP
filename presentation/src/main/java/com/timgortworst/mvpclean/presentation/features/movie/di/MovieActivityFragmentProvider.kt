package com.timgortworst.mvpclean.presentation.features.movie.di

import com.timgortworst.mvpclean.presentation.features.movie.view.MovieDetailsFragment
import com.timgortworst.mvpclean.presentation.features.movie.view.MovieListFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MovieActivityFragmentProvider {

    @ContributesAndroidInjector(modules = [(MovieListModule::class)])
    abstract fun provideMovieListFragment(): MovieListFragment

    @ContributesAndroidInjector(modules = [(MovieDetailsModule::class)])
    abstract fun provideMovieDetailsFragment(): MovieDetailsFragment
}
