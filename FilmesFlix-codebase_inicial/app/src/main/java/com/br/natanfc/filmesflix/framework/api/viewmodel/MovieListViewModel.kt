package com.br.natanfc.filmesflix.framework.api.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.br.natanfc.filmesflix.framework.api.MovieRestApiTask
import com.br.natanfc.filmesflix.data.MovieRepository
import com.br.natanfc.filmesflix.domain.Movie
import com.br.natanfc.filmesflix.implementations.MovieDataSourceImplementation
import com.br.natanfc.filmesflix.usecase.MoviesListUseCase
import java.lang.Exception

class MovieListViewModel : ViewModel() {

    companion object {
        const val TAG = "MovieRepository"
    }

    private val movieRestApiTask = MovieRestApiTask()
    private val movieDataSource = MovieDataSourceImplementation(movieRestApiTask)
    private val movieRepository = MovieRepository(movieDataSource)
    private val moviesListUseCase = MoviesListUseCase(movieRepository)


    private var _moviesList = MutableLiveData<List<Movie>>()
    val moviesList: LiveData<List<Movie>>
        get() = _moviesList

    fun init() {
        getAllMovie()
    }

    private fun getAllMovie() {
        Thread {
            try {
                _moviesList.postValue(moviesListUseCase.invoke())
            } catch (exeption: Exception) {
                Log.d(TAG, exeption.message.toString())
            }
        }.start()
    }

}