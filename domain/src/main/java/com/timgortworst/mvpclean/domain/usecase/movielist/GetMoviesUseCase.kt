package com.timgortworst.mvpclean.domain.usecase.movielist

import com.timgortworst.mvpclean.domain.model.movie.Movie
import com.timgortworst.mvpclean.domain.usecase.FlowUseCase

interface GetMoviesUseCase : FlowUseCase<Unit, List<Movie>>