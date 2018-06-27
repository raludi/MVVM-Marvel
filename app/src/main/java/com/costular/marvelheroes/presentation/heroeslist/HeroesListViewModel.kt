package com.costular.marvelheroes.presentation.heroeslist


import android.arch.lifecycle.MutableLiveData
import android.util.Log
import com.costular.marvelheroes.data.repository.MarvelHeroesRepositoryImpl
import com.costular.marvelheroes.domain.model.MarvelHeroEntity
import com.costular.marvelheroes.presentation.util.BaseViewModel
import com.costular.marvelheroes.presentation.util.SettingsManager
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class HeroesListViewModel @Inject constructor(val heroesRepository: MarvelHeroesRepositoryImpl,
                                              val settingsManager: SettingsManager): BaseViewModel() {

    val heroesListState: MutableLiveData<List<MarvelHeroEntity>> = MutableLiveData()
    val isLoadingState: MutableLiveData<Boolean> = MutableLiveData()
    fun loadHeroesList() {
        if (!settingsManager.firstLoad) {
            loadHeroesListFromDb()
            return
        }
        heroesRepository.getMarvelHeroesList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { isLoadingState.postValue(true) }
                .doOnTerminate { isLoadingState.postValue(false) }
                .subscribeBy (
                        onNext = {
                            heroesListState.value = it
                        },
                        onError = {
                            Log.d("HeroesViewModel", it.toString())
                        },
                        onComplete = {
                            settingsManager.firstLoad = false
                        }
                )
                .addTo(compositeDisposable)
    }
    fun loadHeroesListFromDb() {
        heroesRepository.getMarvelHeroesFromDb()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { isLoadingState.postValue(true) }
                .doOnTerminate { isLoadingState.postValue(false) }
                .subscribeBy (
                        onNext = {
                            heroesListState.value = it
                        },
                        onError = {
                            Log.d("HeroesViewModel", it.toString())
                        }
                )
                .addTo(compositeDisposable)
    }
}