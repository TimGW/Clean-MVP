package com.timgortworst.mvpclean.presentation.di

import com.timgortworst.mvpclean.presentation.features.movie.MovieActivityFragmentProvider
import com.timgortworst.mvpclean.presentation.features.movie.MovieActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilder {

    @ContributesAndroidInjector(modules = [MovieActivityFragmentProvider::class])
    abstract fun bindMainActivity(): MovieActivity
}
