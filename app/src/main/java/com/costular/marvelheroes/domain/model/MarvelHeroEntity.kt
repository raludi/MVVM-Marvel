package com.costular.marvelheroes.domain.model

import android.annotation.SuppressLint
import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.os.Parcel
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * Created by costular on 17/03/2018.
 */
@SuppressLint("ParcelCreator")
@Entity(tableName = "heroes")
@Parcelize
data class MarvelHeroEntity(
        @PrimaryKey
        //val id: Long,
        val name: String,
        val photoUrl: String,
        val realName: String,
        val height: String,
        val power: String,
        val abilities: String,
        val groups: MutableList<String>?,
        var favourite: Boolean
) : Parcelable