package com.timgortworst.mvpclean.presentation.di

import android.content.Context
import com.timgortworst.mvpclean.data.di.*
import com.timgortworst.mvpclean.domain.di.UseCaseModule
import com.timgortworst.mvpclean.presentation.CleanMVP
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        ActivityBuilder::class,
        AndroidInjectionModule::class,
        AppModule::class,
        RepositoryModule::class,
        ApiModule::class,
        DaoModule::class,
        NetworkModule::class,
        UseCaseModule::class
    ]
)
interface AppComponent {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun context(context: Context): Builder

        fun build(): AppComponent
    }

    fun inject(application: CleanMVP)
}

