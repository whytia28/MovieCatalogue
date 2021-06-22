package com.why.movieCatalogue.data.source.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movieEntities")
data class MovieEntity(
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    var id: Int,

    @ColumnInfo(name = "title")
    var title: String?,

    @ColumnInfo(name = "synopsis")
    var synopsis: String?,

    @ColumnInfo(name = "releaseData")
    var releaseDate: String?,

    @ColumnInfo(name = "category")
    var category: String,

    @ColumnInfo(name = "imagePoster")
    var imagePoster: String?,

    @ColumnInfo(name = "isFav")
    var isFav: Boolean = false
)