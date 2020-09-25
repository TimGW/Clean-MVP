package com.timgortworst.mvpclean.domain.usecase.moviedetail

import com.timgortworst.mvpclean.domain.model.movie.MovieDetails
import com.timgortworst.mvpclean.domain.model.state.State
import com.timgortworst.mvpclean.domain.usecase.SuspendUseCase

interface GetMovieDetailsUseCase :
    SuspendUseCase<GetMovieDetailsUseCaseImpl.Params, State<MovieDetails>>
