package com.timgortworst.mvpclean.presentation.features.movie.details

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.squareup.picasso.Picasso
import com.timgortworst.cleanarchitecture.presentation.R
import com.timgortworst.mvpclean.data.BuildConfig.BASE_URL_IMAGES
import com.timgortworst.mvpclean.data.BuildConfig.BASE_URL_IMAGES_HIGH_RES
import com.timgortworst.mvpclean.domain.model.movie.MovieDetails
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_movie_details.*
import javax.inject.Inject


class MovieDetailsFragment : Fragment(), MovieDetailsView {
    @Inject
    lateinit var presenter: MovieDetailsPresenter

    private val movieId: Int by lazy {
        arguments?.getInt(MOVIE_ID_BUNDLE_KEY, INVALID_MOVIE_ID) ?: INVALID_MOVIE_ID
    }

    private val posterPath: String by lazy {
        arguments?.getString(MOVIE_POSTER_BUNDLE_KEY, "") ?: ""
    }

    companion object {
        private const val INVALID_MOVIE_ID = -1
        private const val MOVIE_ID_BUNDLE_KEY = "MOVIE_ID_BUNDLE_KEY"
        private const val MOVIE_POSTER_BUNDLE_KEY = "MOVIE_POSTER_BUNDLE_KEY"

        fun newInstance(movieId: Int, moviePoster: String? = null): MovieDetailsFragment {
            val fragment = MovieDetailsFragment()
            val args = Bundle()
            args.putInt(MOVIE_ID_BUNDLE_KEY, movieId)
            moviePoster?.let { args.putString(MOVIE_POSTER_BUNDLE_KEY, it) }
            fragment.arguments = args

            return fragment
        }
    }

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = inflater.inflate(R.layout.fragment_movie_details, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        loadImage(BASE_URL_IMAGES.plus(posterPath))

        presenter.fetchMovieDetails(movieId)
    }

    override fun showLoading() {
        progress_bar?.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        progress_bar?.visibility = View.INVISIBLE
    }

    override fun showError(message: String) {
        error_message.visibility = View.VISIBLE
        error_message.text =
            getString(R.string.no_internet_placeholder_text, message)
    }

    override fun hideError() {
        error_message.visibility = View.GONE
    }

    override fun showResult(movieDetails: MovieDetails) {
        movie_details_title.text = movieDetails.title
        movie_details_release_date.text =
            getString(R.string.movie_detail_release_date, movieDetails.releaseDate)
        movie_details_overview.text = movieDetails.overview

        loadImage(BASE_URL_IMAGES_HIGH_RES.plus(movieDetails.posterPath))
    }

    override fun hideResult() {

    }

    private fun loadImage(url: String) = Picasso.get().load(url)
        .noPlaceholder()
        .noFade()
        .centerCrop()
        .fit()
        .into(movie_details_image)
}
