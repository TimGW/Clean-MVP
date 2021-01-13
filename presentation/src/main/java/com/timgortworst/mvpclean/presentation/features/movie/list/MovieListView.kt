package com.timgortworst.mvpclean.presentation.features.movie.list

import com.timgortworst.mvpclean.domain.model.movie.Movie

interface MovieListView {
    fun showLoading()
    fun hideLoading()

    fun showError(message: String)
    fun hideError()

    fun showResult(data: List<Movie>)
    fun hideResult()
}