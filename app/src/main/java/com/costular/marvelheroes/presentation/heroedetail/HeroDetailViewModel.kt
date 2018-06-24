package com.costular.marvelheroes.presentation.heroedetail

import com.costular.marvelheroes.data.repository.MarvelHeroesRepositoryImpl
import com.costular.marvelheroes.domain.model.MarvelHeroEntity
import com.costular.marvelheroes.presentation.util.BaseViewModel
import io.reactivex.Observable
import java.util.*
import javax.inject.Inject

class HeroDetailViewModel @Inject constructor(val repositoryImpl: MarvelHeroesRepositoryImpl): BaseViewModel() {

    fun updateFavouriteModel(hero: Observable<MarvelHeroEntity>) {
        repositoryImpl.changeFavouriteHero(hero)
    }
}