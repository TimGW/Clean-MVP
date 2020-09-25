package com.timgortworst.mvpclean.presentation.features.movie.view

import com.timgortworst.mvpclean.domain.model.movie.MovieDetails

interface MovieDetailsView {
    fun showLoading()
    fun hideLoading()

    fun showError(message: String)
    fun hideError()

    fun showResult(movieDetails: MovieDetails)
    fun hideResult()
}