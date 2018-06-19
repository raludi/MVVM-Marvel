package com.costular.marvelheroes.data.repository

import com.costular.marvelheroes.data.model.mapper.MarvelHeroMapper
import com.costular.marvelheroes.data.repository.datasource.LocalDataMarvelHeroesDataSource
import com.costular.marvelheroes.data.repository.datasource.RemoteMarvelHeroesDataSource
import com.costular.marvelheroes.domain.model.MarvelHeroEntity
import io.reactivex.Flowable
import io.reactivex.Observable

/**
 * Created by costular on 17/03/2018.
 */
class MarvelHeroesRepositoryImpl(private val remoteMarvelHeroesDataSource: RemoteMarvelHeroesDataSource,
                                 private val localDataMarvelHeroesDataSource: LocalDataMarvelHeroesDataSource)
    : MarvelHeroesRepository {

    override fun getMarvelHeroesList(): Flowable<List<MarvelHeroEntity>> =
        getMarvelHeroesFromDb().concatWith(getMarvelHeroesFromApi())

    override fun getMarvelHeroesFromApi(): Flowable<List<MarvelHeroEntity>> =
            remoteMarvelHeroesDataSource
                    .getMarvelHeroesList()

    override fun getMarvelHeroesFromDb(): Flowable<List<MarvelHeroEntity>> =
            localDataMarvelHeroesDataSource.getMarvelHeroesList()

}