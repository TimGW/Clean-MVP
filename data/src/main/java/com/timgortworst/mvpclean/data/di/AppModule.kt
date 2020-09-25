package com.timgortworst.mvpclean.data.di

import android.content.Context
import androidx.room.Room
import com.timgortworst.mvpclean.data.database.AppDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule {

    @Provides
    @Singleton
    fun provideAppDatabase(context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "movie_database"
        )
            .fallbackToDestructiveMigration()
            .build()
    }
}
