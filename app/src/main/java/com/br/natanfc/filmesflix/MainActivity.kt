package com.br.natanfc.filmesflix

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.br.natanfc.filmesflix.domain.Movie
import com.br.natanfc.filmesflix.framework.api.viewmodel.MovieListViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var movieListViewModel: MovieListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        movieListViewModel = ViewModelProvider.NewInstanceFactory().create(MovieListViewModel::class.java)
        movieListViewModel.init() // init() chamada de MovieListViewModel
        initObserver()
        loadingVisibility(true)
    }

    private fun initObserver() {
        movieListViewModel.moviesList.observe(this, Observer { list ->
            if(list.isNotEmpty())  {
                populateList(list)
                loadingVisibility(false)
            }
        })
    }

    private fun populateList(list: List<Movie>) {
        moviesList.apply {
            hasFixedSize() // o conteúdo gerado pelo recyclerview vai ter o mesmo tamanho
            adapter = MoviesAdapter(list)
        }
    }

    private fun loadingVisibility(isLoading: Boolean) {
        if(isLoading) {
            progressBar.visibility = View.VISIBLE
        } else {
            progressBar.visibility = View.GONE
        }

    }

}

