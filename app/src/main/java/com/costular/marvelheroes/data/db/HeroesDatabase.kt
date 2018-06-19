package com.costular.marvelheroes.data.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.TypeConverters
import com.costular.marvelheroes.domain.model.MarvelHeroEntity

@Database(entities = [MarvelHeroEntity::class], version = 1)
@TypeConverters(Converters::class)
abstract class HeroesDatabase: RoomDatabase() {

    abstract fun getHeroDao(): HeroDao

}