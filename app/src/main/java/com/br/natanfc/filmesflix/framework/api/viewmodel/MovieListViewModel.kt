package com.br.natanfc.filmesflix.framework.api.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.br.natanfc.filmesflix.framework.api.MovieRestApiTask
import com.br.natanfc.filmesflix.data.MovieRepository
import com.br.natanfc.filmesflix.domain.Movie
import com.br.natanfc.filmesflix.implementations.MovieDataSourceImplemantation

import com.br.natanfc.filmesflix.usecase.MovieListUseCase
import java.lang.Exception

class MovieListViewModel: ViewModel() {

    companion object {
        const val TAG = "MovieRepository"
    }

    private val movieRestApiTask = MovieRestApiTask()
    private val movieDataSource = MovieDataSourceImplemantation(movieRestApiTask)
    private val movieRepository = MovieRepository(movieDataSource)
    private val movieListUseCase = MovieListUseCase(movieRepository)


    private var _moviesList = MutableLiveData<List<Movie>>()  // é um livedata do tipo List<Movie> que pode ser alterado
    val moviesList: LiveData<List<Movie>> // boa prática: criar um livedata não mutável para ser observada em vez de deixar a mutável como pública. PS: O LiveData é alterado quando o MutableLiveData é alterado(ele está observando)
    get() = _moviesList

    fun init() { // variavel _moviesList recebe a lista listOfMovies
        getAllMovies()
    }

    private fun getAllMovies() {
        Thread { // rodar esse processo em outra thread para não bloquear a thread principal
            try {
                _moviesList.postValue(movieListUseCase.invoke())
            } catch (exception: Exception) {
                Log.d(TAG, exception.message.toString())
            }
        }.start()

    }

}