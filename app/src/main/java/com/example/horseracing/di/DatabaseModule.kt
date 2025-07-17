package com.example.horseracing.di

import android.content.Context
import com.example.horseracing.data.local.AppDatabase
import com.example.horseracing.data.local.dao.RaceDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Provides
    @Singleton
    fun provideHistoryDayDao(database: AppDatabase): RaceDao {
        return database.raceDao()
    }

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return AppDatabase.getDataBase(context)
    }
}