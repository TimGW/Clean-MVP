package com.timgortworst.mvpclean.presentation.di

import com.timgortworst.mvpclean.presentation.features.movie.di.MovieActivityFragmentProvider
import com.timgortworst.mvpclean.presentation.features.movie.view.MovieActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector
import kotlinx.coroutines.InternalCoroutinesApi

@Module
abstract class ActivityBuilder {

    @ContributesAndroidInjector(modules = [MovieActivityFragmentProvider::class])
    abstract fun bindMainActivity(): MovieActivity
}
