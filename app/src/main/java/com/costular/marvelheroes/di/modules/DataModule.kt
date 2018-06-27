package com.costular.marvelheroes.di.modules

import android.arch.persistence.room.Room
import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import com.costular.marvelheroes.data.db.HeroesDatabase
import com.costular.marvelheroes.data.model.mapper.MarvelHeroMapper
import com.costular.marvelheroes.data.net.MarvelHeroesService
import com.costular.marvelheroes.data.repository.MarvelHeroesRepositoryImpl
import com.costular.marvelheroes.data.repository.datasource.LocalDataMarvelHeroesDataSource
import com.costular.marvelheroes.data.repository.datasource.RemoteMarvelHeroesDataSource
import com.costular.marvelheroes.presentation.util.SettingsManager
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by costular on 17/03/2018.
 */
@Module
class DataModule {

    @Provides
    @Singleton
    fun provideMarvelHeroMapper(): MarvelHeroMapper = MarvelHeroMapper()

    @Provides
    @Singleton
    fun provideDatabase(context: Context)
            : HeroesDatabase =
            Room.databaseBuilder(context,HeroesDatabase::class.java, "heroes.db").build()

    @Singleton
    @Provides
    fun provideSharedPreferences(context: Context): SharedPreferences =
            PreferenceManager.getDefaultSharedPreferences(context)

    @Singleton
    @Provides
    fun provideSettingsManager(sharedPreferences: SharedPreferences): SettingsManager =
            SettingsManager(sharedPreferences)

    @Provides
    @Singleton
    fun provideRemoteMarvelHeroesDataSoruce(marvelHeroesService: MarvelHeroesService,
                                            marvelHeroMapper: MarvelHeroMapper)
            : RemoteMarvelHeroesDataSource =
            RemoteMarvelHeroesDataSource(marvelHeroesService, marvelHeroMapper)

    @Provides
    @Singleton
    fun provideLocalMarvelHeroesDataSource(heroesDatabase: HeroesDatabase)
            : LocalDataMarvelHeroesDataSource =
            LocalDataMarvelHeroesDataSource(heroesDatabase)


    @Provides
    @Singleton
    fun provideMarvelHeroesRepository(
            marvelRemoteMarvelHeroesDataSource: RemoteMarvelHeroesDataSource,
            localDataMarvelHeroesDataSource: LocalDataMarvelHeroesDataSource): MarvelHeroesRepositoryImpl =
            MarvelHeroesRepositoryImpl(marvelRemoteMarvelHeroesDataSource, localDataMarvelHeroesDataSource)


}