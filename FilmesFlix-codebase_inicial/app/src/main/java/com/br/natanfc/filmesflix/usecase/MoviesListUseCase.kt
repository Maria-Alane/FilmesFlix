package com.br.natanfc.filmesflix.usecase


class MoviesListUseCase(private val movieRepository: com.br.natanfc.filmesflix.data.MovieRepository) {

    operator fun invoke() = movieRepository.getAllMovieFromDataSource()

}