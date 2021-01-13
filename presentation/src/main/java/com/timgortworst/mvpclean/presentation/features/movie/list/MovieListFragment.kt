package com.timgortworst.mvpclean.presentation.features.movie.list

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.timgortworst.cleanarchitecture.presentation.R
import com.timgortworst.mvpclean.domain.model.movie.Movie
import com.timgortworst.mvpclean.presentation.extension.snackbar
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_movie_list.*
import javax.inject.Inject

class MovieListFragment : Fragment(), MovieListView {
    @Inject
    lateinit var presenter: MovieListPresenter

    private lateinit var adapter: MovieListAdapter
    private var movieDetailsClickListener: MovieDetailsClickListener? = null

    companion object {
        fun newInstance(): MovieListFragment {
            val fragment = MovieListFragment()
            val args = Bundle()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)

        if (context is MovieDetailsClickListener) {
            movieDetailsClickListener = context
        } else {
            throw ClassCastException(context.toString() + " must implement MovieDetailsClickListener")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_movie_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val activity = activity as? AppCompatActivity ?: return
        activity.setSupportActionBar(toolbar)
        activity.title = getString(R.string.movie_list_toolbar_title)
        swiperefresh?.isEnabled = false

        setupMovieList()

        presenter.fetchMovies()
    }

    override fun onDetach() {
        movieDetailsClickListener = null
        super.onDetach()
    }

    override fun showLoading() {
        swiperefresh?.isRefreshing = true
    }

    override fun hideLoading() {
        swiperefresh?.isRefreshing = false
    }

    override fun showError(message: String) {
        view?.snackbar(message)
    }

    override fun hideError() {

    }

    override fun showResult(data: List<Movie>) {
        no_results?.visibility = View.GONE
        adapter.addMoviesToList(data.toMutableList())
    }

    override fun hideResult() {
        no_results?.visibility = View.VISIBLE
    }

    private fun setupMovieList() {
        adapter = MovieListAdapter(mutableListOf()) { movie, moviePoster, transitionName ->
            movieDetailsClickListener?.onMovieClicked(movie, moviePoster, transitionName)
        }

        val columns = resources.getInteger(R.integer.gallery_columns)
        val orientation = resources.getInteger(R.integer.gallery_orientation)

        movie_list?.layoutManager = GridLayoutManager(activity, columns, orientation, false)
        movie_list?.adapter = adapter
    }

    interface MovieDetailsClickListener {
        fun onMovieClicked(movie: Movie, moviePoster: ImageView, transitionName: String)
    }
}
