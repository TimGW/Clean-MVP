package com.timgortworst.mvpclean.presentation

import android.app.Activity
import android.app.Application
import android.content.Context
import com.timgortworst.mvpclean.presentation.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject

open class CleanMVP : Application(), HasActivityInjector {

    @Inject
    @JvmField
    var activityInjector: DispatchingAndroidInjector<Activity>? = null

    override fun onCreate() {
        super.onCreate()
        instance = this

        DaggerAppComponent.builder()
            .context(this)
            .build()
            .inject(this)
    }

    override fun activityInjector(): AndroidInjector<Activity> {
        return activityInjector!!
    }

    companion object {
        var instance: CleanMVP? = null
            private set

        val context: Context?
            get() = instance?.applicationContext
    }
}