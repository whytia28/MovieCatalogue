package com.why.movieCatalogue.utils

import androidx.sqlite.db.SimpleSQLiteQuery

object SortUtils {
    const val ASCEND = "Ascend"
    const val DESCEND = "Descend"
    const val RANDOM = "Random"
    const val MOVIE_ENTITIES = "movieEntities"
    const val TV_SHOW_ENTITIES = "tvShowEntities"

    fun getSortedQuery(filter: String, table_name: String): SimpleSQLiteQuery {
        val simpleQuery = StringBuilder().append("SELECT * FROM $table_name ")
        when (filter) {
            ASCEND -> simpleQuery.append("ORDER BY title ASC")
            DESCEND -> simpleQuery.append("ORDER BY title DESC")
            RANDOM -> simpleQuery.append("ORDER BY RANDOM()")
        }
        return SimpleSQLiteQuery(simpleQuery.toString())
    }
}