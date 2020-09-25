package com.timgortworst.mvpclean.domain.repository

import com.timgortworst.mvpclean.domain.model.movie.Movie
import com.timgortworst.mvpclean.domain.model.movie.MovieDetails
import kotlinx.coroutines.flow.Flow
import com.timgortworst.mvpclean.domain.model.state.State

interface MovieRepository {
    /**
     * Fetch the movies from network and stored it in database. At the end, data from persistence
     * storage is fetched and emitted.
     */
    fun getMoviesFlow(): Flow<State<List<Movie>>>

    /**
     * one shot operation to fetch the movie details from network
     */
    suspend fun getMovieDetails(movieId: Int): State<MovieDetails>
}
