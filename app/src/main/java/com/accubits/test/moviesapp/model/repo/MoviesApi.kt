package com.accubits.test.moviesapp.model.repo

import com.accubits.test.moviesapp.model.data.MoviesWrapperResponse
import retrofit2.http.GET

/**
 * @author Rolbin, created on 08-01-2021
 */
interface MoviesApi {

    @GET("3/movie/upcoming?api_key=9c0523bff54071c4fb4b716a950231b9&language=en-US&page=1&region=IN|US&with_release_type=2|3")
    suspend fun getMovies(): MoviesWrapperResponse

}