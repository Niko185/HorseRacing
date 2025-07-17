package com.example.horseracing.di

import com.example.horseracing.data.local.race.repository.HistoryRepositoryImpl
import com.example.horseracing.data.local.race.repository.RaceDao
import com.example.horseracing.data.local.race.repository.RaceRepositoryImpl
import com.example.horseracing.domain.repository.HistoryRepository
import com.example.horseracing.domain.repository.RaceRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Provides
    @Singleton
    fun provideHistoryRepository(raceDao: RaceDao): HistoryRepository {
        return HistoryRepositoryImpl(raceDao)
    }

    @Provides
    @Singleton
    fun provideRaceRepository(): RaceRepository {
        return RaceRepositoryImpl()
    }
}