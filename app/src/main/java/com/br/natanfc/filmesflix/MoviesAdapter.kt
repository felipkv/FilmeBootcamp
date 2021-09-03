package com.br.natanfc.filmesflix

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.br.natanfc.filmesflix.domain.Movie
import kotlinx.android.synthetic.main.movie_item_layout.view.*

class MoviesAdapter(private val moviesList: List<Movie>): RecyclerView.Adapter<MoviesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.movie_item_layout, parent, false)
        return MoviesViewHolder(view)
    }

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        holder.itemView.apply { // vai aplicar os métodos em cada um da lista, evitar de ter que evocar holder.itemView para cada um
            movieTitle.text = moviesList[position].titulo
            movieImage.load(moviesList[position].imagem) {
                placeholder(R.drawable.ic_image) // placeholder enquanto não renderiza
                fallback(R.drawable.ic_image) // caso não tenha imagem para ser renderizada, retorna isso(no caso do projeto está como null)
            }
        }
    }

    override fun getItemCount(): Int {
        return moviesList.size
    }
}