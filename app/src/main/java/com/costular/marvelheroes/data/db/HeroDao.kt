package com.costular.marvelheroes.data.db

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.costular.marvelheroes.domain.model.MarvelHeroEntity
import io.reactivex.Maybe

@Dao
abstract class HeroDao {

    @Query("SELECT * FROM heroes")
    abstract fun getAllHeroes(): Maybe<List<MarvelHeroEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insert(heroes: List<MarvelHeroEntity>)

    @Query("DELETE FROM heroes")
    abstract fun deleteAll()
}