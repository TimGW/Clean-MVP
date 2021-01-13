package com.timgortworst.mvpclean.presentation.features.movie.list

import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import com.timgortworst.mvpclean.domain.model.state.State
import com.timgortworst.mvpclean.domain.usecase.movielist.GetMoviesUseCase
import com.timgortworst.mvpclean.presentation.features.movie.list.MovieListView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class MovieListPresenter(
    private val view: MovieListView,
    private val getMoviesUseCase: GetMoviesUseCase
) : CoroutineScope, DefaultLifecycleObserver {

    private lateinit var job: Job
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job

    init {
        if (view is LifecycleOwner) {
            (view as LifecycleOwner).lifecycle.addObserver(this)
        }
    }

    override fun onCreate(owner: LifecycleOwner) {
        job = Job()
    }

    override fun onDestroy(owner: LifecycleOwner) {
        job.cancel()
    }

    fun fetchMovies() {
        job = launch {
            getMoviesUseCase.execute(Unit).collect { res ->
                when (res) {
                    State.Loading -> {
                        view.showLoading()
                        view.hideError()
                        view.hideResult()
                    }
                    is State.Success -> {
                        view.hideLoading()
                        view.hideError()

                        if (res.data.isEmpty()) {
                            view.hideResult()
                        } else {
                            view.showResult(res.data)
                        }
                    }
                    is State.Error -> {
                        view.hideLoading()
                        view.showError("Check your internet")
                        view.hideResult()
                    }
                }
            }
        }
    }
}
