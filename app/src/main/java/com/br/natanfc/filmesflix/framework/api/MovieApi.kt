package com.br.natanfc.filmesflix.framework.api

import com.br.natanfc.filmesflix.domain.Movie
import retrofit2.Call
import retrofit2.http.GET

interface MovieApi {

    //https://raw.githubusercontent.com/natanfelipe/FilmesFlixJson/master/moviesList

    @GET("natanfelipe/FilmesFlixJson/master/moviesList")
    fun getAllMovies(): Call<List<Movie>> // call em vez de só list<movie> = acesso a propriedades de sucesso e erro

}