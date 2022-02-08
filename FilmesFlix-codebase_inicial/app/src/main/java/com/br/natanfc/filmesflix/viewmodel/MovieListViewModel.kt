package com.br.natanfc.filmesflix.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.br.natanfc.filmesflix.api.MovieRestApiTask
import com.br.natanfc.filmesflix.model.Movie
import com.br.natanfc.filmesflix.repository.MovieRepository
import java.lang.Exception

class MovieListViewModel : ViewModel() {

    companion object {
        const val TAG = "MovieRepository"
    }

    private val movieRestApiTask = MovieRestApiTask()
    private val movieRepository = MovieRepository(MovieRestApiTask())


    private var _moviesList = MutableLiveData<List<Movie>>()
    val moviesList: LiveData<List<Movie>>
        get() = _moviesList

    fun init() {
        getAllMovie()
    }

    private fun getAllMovie() {
        Thread {
            try {
                _moviesList.postValue(movieRepository.getAllMovie())
            } catch (exeption: Exception) {
                Log.d(TAG, exeption.message.toString())
            }
        }.start()
    }

}