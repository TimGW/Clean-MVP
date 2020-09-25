package com.timgortworst.mvpclean.data.mapper

import com.timgortworst.mvpclean.data.model.DbMovie
import com.timgortworst.mvpclean.data.model.NetworkMovies
import com.timgortworst.mvpclean.domain.model.movie.Movie

fun DbMovie.asDomainModel(): Movie = with(this) {
    Movie(
        adult,
        backdropPath,
        id,
        originalLanguage,
        originalTitle,
        overview,
        popularity,
        posterPath,
        releaseDate,
        title,
        video,
        voteAverage,
        voteCount
    )
}

fun NetworkMovies.asDatabaseModel() : List<DbMovie> = with(this) {
    results.map {
        DbMovie(
            it.id,
            it.adult,
            it.backdropPath,
            it.originalLanguage,
            it.originalTitle,
            it.overview,
            it.popularity,
            it.posterPath,
            it.releaseDate,
            it.title,
            it.video,
            it.voteAverage,
            it.voteCount
        )
    }
}