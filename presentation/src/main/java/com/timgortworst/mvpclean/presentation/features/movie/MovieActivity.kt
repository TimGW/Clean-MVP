package com.timgortworst.mvpclean.presentation.features.movie

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.timgortworst.cleanarchitecture.presentation.R
import com.timgortworst.mvpclean.domain.model.movie.Movie
import com.timgortworst.mvpclean.presentation.features.movie.details.MovieDetailsFragment
import com.timgortworst.mvpclean.presentation.features.movie.list.MovieListFragment
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject


class MovieActivity :
    AppCompatActivity(),
    MovieListFragment.MovieDetailsClickListener,
    HasSupportFragmentInjector {

    @Inject
    lateinit var fragmentDispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>

    companion object {
        fun intentBuilder(context: Context): Intent {
            return Intent(context, MovieActivity::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie)

        if (savedInstanceState == null) {
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.fragment_placeholder, MovieListFragment.newInstance())
            transaction.commit()
        }
    }

    override fun onBackPressed() {
        val fm = supportFragmentManager
        if (fm.backStackEntryCount > 0) {
            fm.popBackStack()
        } else {
            super.onBackPressed()
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_placeholder, fragment)
            .addToBackStack(null)
            .commit()
    }

    override fun onMovieClicked(
        movie: Movie,
        moviePoster: ImageView,
        transitionName: String
    ) {
        replaceFragment(MovieDetailsFragment.newInstance(movie.id, movie.posterPath))
    }

    public override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data) // delegate to fragment
    }

    override fun supportFragmentInjector(): AndroidInjector<Fragment?>? {
        return fragmentDispatchingAndroidInjector
    }
}
