package com.timgortworst.mvpclean.domain.usecase.moviedetail

import com.timgortworst.mvpclean.domain.model.movie.MovieDetails
import com.timgortworst.mvpclean.domain.model.state.State
import com.timgortworst.mvpclean.domain.repository.MovieRepository

class GetMovieDetailsUseCaseImpl(
    private val movieRepository: MovieRepository
) : GetMovieDetailsUseCase {

    data class Params(val movieId: Int)

    override suspend fun execute(params: Params): State<MovieDetails> {
       return when (val result = movieRepository.getMovieDetails(params.movieId)) {
            is State.Success -> State.Success(result.data)
            is State.Error -> State.Error(result.errorEntity)
           State.Loading -> State.Loading
        }
    }
}
