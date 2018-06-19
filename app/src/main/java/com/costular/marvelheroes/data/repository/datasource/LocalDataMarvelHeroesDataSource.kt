package com.costular.marvelheroes.data.repository.datasource

import com.costular.marvelheroes.data.db.HeroesDatabase
import com.costular.marvelheroes.data.model.MarvelHero
import com.costular.marvelheroes.domain.model.MarvelHeroEntity
import io.reactivex.Flowable

/**
 * Created by costular on 05/06/2018.
 */
class LocalDataMarvelHeroesDataSource(val heroesDatabase: HeroesDatabase) : MarvelHeroesDataSource {

    override fun getMarvelHeroesList(): Flowable<List<MarvelHeroEntity>> =
       heroesDatabase.getHeroDao()
               .getAllHeroes()
               .toFlowable()
}