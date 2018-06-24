package com.costular.marvelheroes.data.db

import android.arch.persistence.room.*
import com.costular.marvelheroes.domain.model.MarvelHeroEntity
import io.reactivex.Maybe

@Dao
abstract class HeroDao {

    @Query("SELECT * FROM heroes")
    abstract fun getAllHeroes(): Maybe<List<MarvelHeroEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insert(heroes: List<MarvelHeroEntity>)

    @Update
    abstract fun updateHero(hero: MarvelHeroEntity)

    @Query("DELETE FROM heroes")
    abstract fun deleteAll()

    @Transaction
    open fun removeAndInsertHeroes(heroes: List<MarvelHeroEntity>) {
        deleteAll()
        insert(heroes)
    }
}